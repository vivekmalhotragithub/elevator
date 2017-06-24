package com.tingco.codechallenge.elevator.dto;

/**
 * An Event generated for {@link Elevator} to be consumed. This event has
 * information like destination floor and elevator for which this event is.
 * 
 * @author vivekmalhotra
 *
 */
public class ElevatorMoveEvent {

	private int toFloor;

	private int elevatorId;

	public ElevatorMoveEvent(int toFloor, int elevatorId) {
		this.toFloor = toFloor;
		this.elevatorId = elevatorId;
	}

	/**
	 * @return the toFloor
	 */
	public int getToFloor() {
		return toFloor;
	}

	/**
	 * @return the elevatorId
	 */
	public int getElevatorId() {
		return elevatorId;
	}

}
