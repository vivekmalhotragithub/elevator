package com.tingco.codechallenge.elevator.dto;

import com.tingco.codechallenge.elevator.api.Elevator;

/**
 * Response class to a request made for an Elevator
 * 
 * @author vivekmalhotra
 *
 */
public class ElevatorResponse {

	private Elevator elevator;

	private String response;

	

	/**
	 * @return the elevator
	 */
	public Elevator getElevator() {
		return elevator;
	}

	/**
	 * @param elevator the elevator to set
	 */
	public void setElevator(Elevator elevator) {
		this.elevator = elevator;
	}

	/**
	 * @return the response
	 */
	public String getResponse() {
		return response;
	}

	/**
	 * @param response
	 *            the response to set
	 */
	public void setResponse(String response) {
		this.response = response;
	}

}
