package com.tingco.codechallenge.elevator.api.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.tingco.codechallenge.elevator.api.Elevator;
import com.tingco.codechallenge.elevator.api.Elevator.Direction;

/**
 * Unit test class for {@link ElevatorImpl}
 * @author vivekmalhotra
 *
 */
public class ElevatorTest {
	
	Elevator elevator;
	
	@Before
	public void setup(){
		elevator = new ElevatorImpl(5);
	}

	@Test
	public void testPropertiesWhenElevatorInitialized(){
		//
		Assert.assertEquals(5, elevator.getId());
		Assert.assertEquals(Direction.NONE, elevator.getDirection());
		Assert.assertEquals(0, elevator.currentFloor());
	}
	
	
	
}
