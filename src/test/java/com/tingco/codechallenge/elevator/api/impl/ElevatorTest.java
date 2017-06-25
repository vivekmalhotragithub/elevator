package com.tingco.codechallenge.elevator.api.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.tingco.codechallenge.elevator.api.Elevator.Direction;

/**
 * Unit test class for {@link ElevatorImpl}
 * 
 * @author vivekmalhotra
 *
 */
public class ElevatorTest {

	ElevatorImpl elevator;

	@Before
	public void setup() {
		elevator = new ElevatorImpl(5);
	}

	@Test
	public void testPropertiesWhenElevatorInitialized() {
		// then
		Assert.assertEquals(5, elevator.getId());
		Assert.assertEquals(Direction.NONE, elevator.getDirection());
		Assert.assertEquals(0, elevator.currentFloor());
		Assert.assertFalse(elevator.isBusy());
	}

	@Test
	public void testElevatorShouldMoveUpWhenToFloorIsGreater() {
		// when
		elevator.moveElevator(5);
		elevator.moveElevator(10);

		// test
		Assert.assertEquals(5, elevator.getId());
		Assert.assertEquals(Direction.UP, elevator.getDirection());
		Assert.assertEquals(5, elevator.currentFloor());
		Assert.assertEquals(10, elevator.getAddressedFloor());
		Assert.assertTrue(elevator.isBusy());

	}

	@Test
	public void testElevatorShouldMoveDownWhenToFloorIsLower() {
		// when
		elevator.moveElevator(7);
		elevator.moveElevator(2);

		// test
		Assert.assertEquals(5, elevator.getId());
		Assert.assertEquals(Direction.DOWN, elevator.getDirection());
		Assert.assertEquals(7, elevator.currentFloor());
		Assert.assertEquals(2, elevator.getAddressedFloor());
		Assert.assertTrue(elevator.isBusy());
	}

}
