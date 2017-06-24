package com.tingco.codechallenge.elevator.api.impl;

import com.google.common.eventbus.Subscribe;
import com.tingco.codechallenge.elevator.api.Elevator;
import com.tingco.codechallenge.elevator.dto.ElevatorMoveEvent;

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

	// target floor
	private int addressedFloor;

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
		// get addressed floor
		return this.addressedFloor;
	}

	@Override
	public int getId() {
		// get id of Elevator
		return this.id;
	}

	@Override
	public void moveElevator(int toFloor) {
		// set current floor
		this.currentFloor = this.addressedFloor;
		// set the addressed floor
		this.addressedFloor = toFloor;
		// set the direction of the floor
		this.direction = Direction.getInstance(this.currentFloor, this.addressedFloor);
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

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (this == obj)
			return true;

		if (obj instanceof ElevatorImpl) {
			ElevatorImpl elevator = (ElevatorImpl) obj;
			if (this.id == elevator.id) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		int hashcode = 0;
		hashcode += this.id;
		return hashcode;
	}
	
	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("[ ID: "+this.id);
		builder.append(", current floor: "+ this.currentFloor);
		builder.append(", direction: "+ this.direction + " ]");
		return builder.toString();
	}
	
	@Subscribe
	public void eventHandler(ElevatorMoveEvent event){
		if(event.getElevatorId() == this.id){
			this.moveElevator(event.getToFloor());
		}
	}

	
	
}
