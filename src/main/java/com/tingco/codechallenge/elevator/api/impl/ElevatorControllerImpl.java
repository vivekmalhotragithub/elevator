package com.tingco.codechallenge.elevator.api.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.common.eventbus.EventBus;
import com.tingco.codechallenge.elevator.api.Elevator;
import com.tingco.codechallenge.elevator.api.ElevatorController;

/**
 * <p>
 * Class that represents a building Elevator controller. It implements the
 * {@link ElevatorController}. This controller will be initialized with 1. no of
 * {@link Elevator} and 2. total floors in building.
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
	private List<Elevator> allElevators = new ArrayList<>();

	// total floors in building
	@Value("${com.tingco.elevator.building.floors}")
	private int totalBuildingFloors;

	public ElevatorControllerImpl(@Autowired Executor executor, @Autowired EventBus eventBus,
			@Value("${com.tingco.elevator.numberofelevators}") int numberOfElevators,
			@Value("${com.tingco.elevator.building.floors}") int totalBuildingFloors) {
		LOGGER.info("Initializing Elevator Controller with " + numberOfElevators + " Elevators and Building floors "
				+ totalBuildingFloors);

		// creating all Elevators
		for (int i = 0; i < numberOfElevators; i++) {
			this.allElevators.add(new ElevatorImpl(i));
		}

		this.totalBuildingFloors = totalBuildingFloors;
		this.executor = executor;
		this.eventBus = eventBus;
	}

	@Override
	public Elevator requestElevator(int toFloor) {
		//
		// eventBus.register(object);
		// ((ScheduledExecutorService)executor).
		return null;
	}

	@Override
	public List<Elevator> getElevators() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void releaseElevator(Elevator elevator) {
		// TODO Auto-generated method stub

	}

}
