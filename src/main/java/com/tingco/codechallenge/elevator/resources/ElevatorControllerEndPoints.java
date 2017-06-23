package com.tingco.codechallenge.elevator.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tingco.codechallenge.elevator.dto.ElevatorRequest;
import com.tingco.codechallenge.elevator.dto.ElevatorResponse;

/**
 * Rest Resource.
 *
 * @author Sven Wesley
 *
 */
@RestController
@RequestMapping("/rest/v1")
public final class ElevatorControllerEndPoints {

    /**
     * Ping service to test if we are alive.
     *
     * @return String pong
     */
    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    public String ping() {

        return "pong";
    }
    
    
    @RequestMapping(value = "/elevator/request", method = RequestMethod.POST)
    public ElevatorResponse requestElevator(ElevatorRequest request){
    	return null;
    }
}
