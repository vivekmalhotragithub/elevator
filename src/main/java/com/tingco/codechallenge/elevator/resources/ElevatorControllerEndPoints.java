package com.tingco.codechallenge.elevator.resources;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.eventbus.EventBus;
import com.tingco.codechallenge.elevator.api.Elevator;
import com.tingco.codechallenge.elevator.api.ElevatorController;
import com.tingco.codechallenge.elevator.api.impl.ElevatorImpl;
import com.tingco.codechallenge.elevator.dto.ElevatorRequest;
import com.tingco.codechallenge.elevator.dto.ElevatorResponse;

/**
 * Rest Controller for Elevator service.
 *
 * @author vivekmalhotra
 *
 */
@RestController
@RequestMapping("/rest/v1")
public final class ElevatorControllerEndPoints {
	// Logger
	private static final Logger LOGGER = LoggerFactory.getLogger(ElevatorControllerEndPoints.class);

	// event bus
	private EventBus eventBus;

	// elevator controller
	private ElevatorController elevatorController;

	@Autowired
	public ElevatorControllerEndPoints(EventBus eventBus, ElevatorController elevatorController) {
		this.eventBus = eventBus;
		this.elevatorController = elevatorController;
	}

	/**
	 * Ping service to test if we are alive.
	 *
	 * @return String pong
	 */
	@RequestMapping(value = "/ping", method = RequestMethod.GET)
	public String ping() {
		return "pong";
	}

	/**
	 * POST service to request an Elevator to a particular floor
	 * 
	 * @param elevatorRequest
	 *            {@link ElevatorRequest}
	 * @return response in an {@link ElevatorResponse}
	 */
	@RequestMapping(value = "/elevator/request", method = RequestMethod.POST)
	public @ResponseBody ElevatorResponse requestElevator(@RequestBody ElevatorRequest elevatorRequest) {
		LOGGER.info("Elevator request received: " + elevatorRequest);
		Elevator elevator = elevatorController.requestElevator(elevatorRequest.getToFloor());
		eventBus.post(elevatorRequest);
		ElevatorResponse response = new ElevatorResponse();
		response.setResponse("Your request will be taken by Elevator " + elevator.getId());
		response.setElevator(elevator);
		return response;
	}

	/**
	 * GET details of all Elevators
	 * 
	 * @return
	 */
	@RequestMapping(value = "/elevator/all", method = RequestMethod.GET)
	public List<Elevator> getAllElevators() {
		LOGGER.info("get details of all Elevators");
		return elevatorController.getElevators();
	}

	/**
	 * GET details of all Elevators
	 * 
	 * @return
	 */
	@RequestMapping(value = "/elevator/{elevatorId}/release", method = RequestMethod.GET)
	public @ResponseBody ElevatorResponse releaseElevator(@PathVariable Integer elevatorId) {
		LOGGER.info("Request to release Elevator:" + elevatorId);
		Elevator newElevator = new ElevatorImpl(elevatorId);
		elevatorController.releaseElevator(newElevator);
		ElevatorResponse response = new ElevatorResponse();
		response.setResponse("Elevator " + newElevator.getId() + " is released for new future requests.");
		response.setElevator(newElevator);
		return response;
	}
}
