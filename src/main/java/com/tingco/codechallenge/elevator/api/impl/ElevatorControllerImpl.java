package com.tingco.codechallenge.elevator.api.impl;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.common.eventbus.EventBus;
import com.tingco.codechallenge.elevator.api.Elevator;
import com.tingco.codechallenge.elevator.api.ElevatorController;
import com.tingco.codechallenge.elevator.dto.ElevatorMoveEvent;

/**
 * <p>
 * Class that represents a building Elevator controller. It implements the
 * {@link ElevatorController}. This controller will be initialized with
 * <ul>
 * <li>no of {@link Elevator}</li>
 * <li>total floors in building</li>
 * </ul>
 * This elevator controller assums that ground floor is denoted by 0th floor and
 * Top most floor is nth floor defined in application configuration.
 * </p>
 * 
 * @author vivekmalhotra
 *
 */
@Component
public class ElevatorControllerImpl implements ElevatorController {

	// Logger
	private static final Logger LOGGER = LoggerFactory.getLogger(ElevatorControllerImpl.class);

	// Executor
	private Executor executor;

	// Event bus
	private EventBus eventBus;

	// All elevators in a building
	private List<Elevator> allElevators = Collections.synchronizedList(new LinkedList<Elevator>());

	// total floors in building
	private int totalBuildingFloors;

	public ElevatorControllerImpl(@Autowired Executor executor, @Autowired EventBus eventBus,
			@Value("${com.tingco.elevator.numberofelevators}") int numberOfElevators,
			@Value("${com.tingco.elevator.building.floors}") int totalBuildingFloors) {
		LOGGER.info("Initializing Elevator Controller with " + numberOfElevators + " Elevators and Building floors "
				+ totalBuildingFloors);

		this.totalBuildingFloors = totalBuildingFloors;
		this.executor = executor;
		this.eventBus = eventBus;
		Elevator elevator = null;
		// creating all Elevators
		for (int i = 1; i <= numberOfElevators; i++) {
			elevator = new ElevatorImpl(i);
			this.eventBus.register(elevator);
			this.allElevators.add(elevator);
		}

	}

	@Override
	public Elevator requestElevator(final int toFloor) {
		// if requested floor is more than the building floors or less than 0
		if (totalBuildingFloors - 1 < toFloor || toFloor < 0) {
			String msg = String.format("No floor %s exists in building of %s floors ", toFloor,
					this.totalBuildingFloors);
			LOGGER.error(msg);
			throw new IllegalArgumentException(msg);
		}
		Elevator selected = null;
		LOGGER.info("Elevator requested for Floor:" + toFloor);
		final Optional<Elevator> selectedElevator = findElevator(toFloor);
		if (selectedElevator.isPresent()) {

			((ScheduledExecutorService) this.executor).schedule(new Runnable() {
				@Override
				public void run() {
					//
					ElevatorMoveEvent event = new ElevatorMoveEvent(toFloor, selectedElevator.get().getId());
					ElevatorControllerImpl.this.eventBus.post(event);
				}
			}, 100, TimeUnit.MILLISECONDS);
			LOGGER.info("Request for Floor:" + toFloor + " will be taken by Elevator:" + selectedElevator.get());
			return selectedElevator.get();
		}

		return selected;
	}

	// find an Elevator to go to particular floor
	private Optional<Elevator> findElevator(int toFloor) {
		Optional<Elevator> elevator = allElevators.stream()
				.filter(a -> Elevator.Direction.NONE.equals(a.getDirection())).findAny();
		if (!elevator.isPresent()) {

			elevator = allElevators.stream()
					.filter(a -> a.getDirection() == Elevator.Direction.getInstance(a.getCurrentFloor(), toFloor))
					.findAny();
			if (!elevator.isPresent()) {

				elevator = allElevators.stream().findAny();
			}
		}

		return elevator;
	}

	@Override
	public List<Elevator> getElevators() {
		// return all elevators
		return this.allElevators;
	}

	@Override
	public void releaseElevator(Elevator elevator) {
		//
		Optional<Elevator> selectedElevator = this.allElevators.stream().filter(a -> a.getId() == elevator.getId())
				.findFirst();
		if (!selectedElevator.isPresent()) {
			String msg = "No such Elevator exists for the given condition.";
			LOGGER.error(msg);
			throw new IllegalArgumentException(msg);
		}
		LOGGER.info("Reset elevator:" + selectedElevator.get());
		this.allElevators.remove(selectedElevator.get());
		this.allElevators.add(elevator.getId() - 1, elevator);
		this.eventBus.unregister(selectedElevator.get());
	}

}
