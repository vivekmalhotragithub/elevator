package com.tingco.codechallenge.elevator.dto;

import javax.validation.constraints.NotNull;

/**
 * Class which represents an Elevator request
 * 
 * @author vivekmalhotra
 *
 */
public class ElevatorRequest {

	// floor from which request was made
	private int toFloor;

	// floor where you want to go
	private int destinationFloor;

	@NotNull
	// timestamp when request was made
	private long requestTimestamp;

	/**
	 * @return the currentFloor
	 */
	public int getToFloor() {
		return toFloor;
	}

	/**
	 * @param toFloor
	 *            the currentFloor to set
	 */
	public void setToFloor(int toFloor) {
		this.toFloor = toFloor;
	}

	/**
	 * @return the destinationFloor
	 */
	public int getDestinationFloor() {
		return destinationFloor;
	}

	/**
	 * @param destinationFloor
	 *            the destinationFloor to set
	 */
	public void setDestinationFloor(int destinationFloor) {
		this.destinationFloor = destinationFloor;
	}

	/**
	 * @return the requestTimestamp
	 */
	public long getRequestTimestamp() {
		return requestTimestamp;
	}

	/**
	 * @param requestTimestamp
	 *            the requestTimestamp to set
	 */
	public void setRequestTimestamp(long requestTimestamp) {
		this.requestTimestamp = requestTimestamp;
	}

}
