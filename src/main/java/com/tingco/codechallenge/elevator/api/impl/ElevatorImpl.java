package com.tingco.codechallenge.elevator.api.impl;

import com.tingco.codechallenge.elevator.api.Elevator;

/**
 * Implementation of {@link Elevator}. Each Elevator is identified by a unique
 * id and initialized at ground floor. an Elevator has these properties:
 * <ul>
 * <li>Id, unique identifier of Elevator</li>
 * <li>Direction, current direction of Elevator</li>
 * <li>Current Floor, current floor where Elevator is located</li>
 * </ul>
 * 
 * @author vivekmalhotra
 *
 */
public class ElevatorImpl implements Elevator {

	// identifier of the Elevator
	private int id;

	// current direction of the Elevator
	private Direction direction;

	// current floor
	private int currentFloor;

	public ElevatorImpl(int identifier) {
		this.id = identifier;
		this.direction = Direction.NONE;
		this.currentFloor = 0;
	}

	@Override
	public Direction getDirection() {
		// return the current direction of Elevator
		return this.direction;
	}

	@Override
	public int getAddressedFloor() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getId() {
		//
		return this.id;
	}

	@Override
	public void moveElevator(int toFloor) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isBusy() {
		// check if elevator is moving
		return Direction.DOWN.equals(this.direction) || Direction.UP.equals(this.direction);
	}

	@Override
	public int currentFloor() {
		// current floor
		return this.currentFloor;
	}

}
