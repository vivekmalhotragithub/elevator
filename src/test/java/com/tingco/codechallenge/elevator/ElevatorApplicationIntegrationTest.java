package com.tingco.codechallenge.elevator;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Random;

import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
 * Integration test case for {@link ElevatorApplication}
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = ElevatorApplication.class)
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class ElevatorApplicationIntegrationTest {

	private long simulation = 1000L;

	@Autowired
	TestRestTemplate restTemplate;

	@Value("${com.tingco.elevator.building.floors}")
	int totalFloors;

	Random random;

	// set headers
	HttpHeaders headers;

	BufferedWriter logstream;

	@Before
	public void setup() throws IOException {
		random = new Random();
		logstream = Files.newBufferedWriter(Paths.get("./simulationreport.txt"));
		headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
	}

	@Test
	public void simulateAnElevatorShaft() throws IOException {
		JSONObject jsonRequest = null;
		logstream.write("Report generated on : " + new Date());
		logstream.newLine();
		logstream.write("Total simulations :" + simulation);
		logstream.newLine();
		for (int i = 1; i <= simulation; i++) {
			// request for a floor
			int toFloor = random.nextInt(totalFloors);
			jsonRequest = new JSONObject();
			jsonRequest.put("toFloor", toFloor);
			logstream.write("Requested URL: /rest/v1/elevator/request");
			logstream.newLine();
			logstream.write("Request entity :" + jsonRequest);
			logstream.newLine();
			String response = restTemplate.postForEntity("/rest/v1/elevator/request",
					new HttpEntity<String>(jsonRequest.toString(), headers), String.class).getBody();
			logstream.write(response);
			logstream.newLine();

			// elevator status
		}

	}

	@After
	public void destroy() throws IOException {
		logstream.close();
	}

}
