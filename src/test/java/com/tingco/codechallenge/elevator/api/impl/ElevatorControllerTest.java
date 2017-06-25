package com.tingco.codechallenge.elevator.api.impl;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.tingco.codechallenge.elevator.api.Elevator;
import com.tingco.codechallenge.elevator.api.Elevator.Direction;
import com.tingco.codechallenge.elevator.api.ElevatorController;

/**
 * Unit Test class for {@link ElevatorControllerImpl}
 * 
 * @author vivekmalhotra
 *
 */
public class ElevatorControllerTest {

	private EventBus eventBus;
	private ScheduledExecutorService executor;
	private ElevatorController controller;
	private static final int TOTAL_ELEVATORS = 4;
	private static final int TOTAL_FLOORS = 20;

	@Before
	public void setup() {
		eventBus = new AsyncEventBus(Executors.newSingleThreadExecutor());
		executor = Executors.newScheduledThreadPool(TOTAL_ELEVATORS);
		controller = new ElevatorControllerImpl(executor, eventBus, TOTAL_ELEVATORS, TOTAL_FLOORS);
	}

	@Test
	public void testNoOfElevators() {
		//
		Assert.assertEquals(TOTAL_ELEVATORS, controller.getElevators().size());
	}

	@Test
	public void testRequestElevator() {
		// when
		int toFloor = new Random().nextInt(TOTAL_FLOORS);
		Elevator elevator = controller.requestElevator(toFloor);

		// then
		Assert.assertNotNull(elevator);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRequestElevatorBelowGroundFloor() {
		controller.requestElevator(-5);

	}

	@Test(expected = IllegalArgumentException.class)
	public void testRequestElevatorMoreThanBuldingFloor() {
		controller.requestElevator(TOTAL_FLOORS + 1);

	}

	@Test
	public void testReleaseElevator() {
		// when
		Elevator elevator = new ElevatorImpl(3);
		controller.releaseElevator(elevator);
		// then
		Elevator newElevator = controller.getElevators().get(2);
		Assert.assertEquals(3, newElevator.getId());
		Assert.assertEquals(Direction.NONE, newElevator.getDirection());

	}

}
