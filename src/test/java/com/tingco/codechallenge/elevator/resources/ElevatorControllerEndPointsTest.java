package com.tingco.codechallenge.elevator.resources;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.tingco.codechallenge.elevator.config.ElevatorApplication;

/**
 * Boiler plate test class to get up and running with a test faster.
 *
 * @author Sven Wesley
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = ElevatorApplication.class)
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class ElevatorControllerEndPointsTest {

	@Autowired
	TestRestTemplate restTemplate;

	@Test
	public void testGetAllElevator() {
		// when
		String response = restTemplate.getForEntity("/rest/v1/elevator/all", String.class).getBody();
		JSONArray elevators = new JSONArray(response);

		// then
		Assert.assertEquals(10, elevators.length());
	}

	@Test
	public void testRequestElevator() {
		// when
		JSONObject jsonRequest = new JSONObject();
		jsonRequest.put("toFloor", 5);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String response = restTemplate.postForEntity("/rest/v1/elevator/request",
				new HttpEntity<String>(jsonRequest.toString(), headers), String.class).getBody();
		// then
		JSONObject json = new JSONObject(response);
		JSONObject elevator = json.getJSONObject("elevator");
		Assert.assertTrue(elevator.getInt("id") > 0);
	}

}
