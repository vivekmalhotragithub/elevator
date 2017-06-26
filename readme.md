# Elevator Coding Challenge

Create an elevator controller!

This is a skeleton project with two interfaces that you must implement.

You are going to create an Elevator Controller and a number of Elevators that will be managed by the controller. There are a few extra classes already added to the project to get you up and running quickly.

## To Do

There are two interfaces to implement.

 * `Elevator` - this is the elevator itself and has a few public methods to take care of. There can be n number of elevators at the same time

 * `ElevatorController` - this is the elevator manager that keeps track of all the elevators running in the elevator shaft. There should be only one ElevatorController

### Bonus Classes

There are a few classes added to get you faster up and running. It is not mandatory to use these classes in your solution but you can use them to cut time in boiler plate coding.

 * `ElevatorControllerEndPoints` for REST features. If you would like to use them in a test or to support a GUI here is already a basic skeleton class for you

 * `ElevatorApplication` class for starting the Spring container and there are a few beans you can use as well

 * There are two test classes added to get you up and running faster with tests and simulations

## What We Expect

Implement the elevator system and make it as real as possible when it comes to the logic. Which elevator is best suited for a waiting floor, more requests than available elevators and so on.

Write a test or a simulation that runs the system with a number of floors and elevators. The numbers should be flexible and the system must work with one to many elevators.

Document how we start, simulate and monitor your solution. If there is a GUI or logging for monitoring does not matter as long as you describe how it is supposed to be done.

Have fun! This is not a trap. It is a code challenge to check coding style etc. If there are features you don't have time to add or if you have future changes in mind, write comments or document them.

### Deliver Your Solution

Add the code to a github or bitbucket repository. You can also make an archive of the project and e-mail it to us. We would like to see your solution within 7 days.
 
## Build And Run (as is)

As the project is, the Spring app can be started as seen below.

build and run the code with Maven

    mvn package
    mvn spring-boot:run

or start the target JAR file 

    mvn package
    java -jar target/elevator-1.0-SNAPSHOT.jar

## Solution Documentation
by Vivek Malhotra
# Important classes
ElevatorImpl: implementation of Elevator interface. I changed the method to get the current flor to getCurrentFloor so that we get this information in json object.

ElevatorControllerImpl: This is implementation of ElevatorController interface.This controller will be initialized with
 * no of Elevator
 * n - total floors in building
This elevator controller assumes that ground floor is denoted by 0th floor and Top most floor is (n-1)th floor defined in application configuration.

ElevatorControllerEndPoint: This is the REST controller 


# Application properties
com.tingco.elevator.numberofelevators: number of elevators in the building
com.tingco.elevator.building.floors: total floors in a building

# Elevator API

* /rest/v1/elevator/all GET
get all the Elevators and their details currently configured for the building
json response
[  
   {  
      "id":1,
      "direction":"NONE",
      "currentFloor":0,
      "addressedFloor":0,
      "busy":false
   },
   {  
      "id":2,
      "direction":"NONE",
      "currentFloor":0,
      "addressedFloor":0,
      "busy":false
   }
]


* /rest/v1/elevator/request POST
request for an Elevator to a particular floor. You get the Elevator details in response which will attend the request.
Json request
{
	"toFloor": 8
}
Json response
{
    "elevator": {
        "id": 1,
        "direction": "NONE",
        "currentFloor": 0,
        "addressedFloor": 0,
        "busy": false
    },
    "response": "Your request will be taken by Elevator 1"
}

* /rest/v1/elevator/{elevatorId}/release GET
Release the elevator with id for new future requests
json response
{  
   "elevator":{  
      "id":1,
      "direction":"NONE",
      "currentFloor":0,
      "addressedFloor":0,
      "busy":false
   },
   "response":"Elevator 1 is released for new future requests."
}


# Unit tests and Simulations

ElevatorTest, ElevatorControllerTest: JUnit test classes for ElevatorImpl and ElevatorControllerImpl

ElevatorApplicationIntegrationTest : Integration test class for {@link ElevatorApplication}. Use this class for Simulation purpose to check the behavior of the Controller logic to select Elevator for a requested floor. You can change the no of simulations using simulation property. This class uses application-integrationtest.properties.

# Logging and Monitoring
for logging we are using sl4j. This can be configured to write to log stream in containerized environment.

for monitoring the elevators you can use the /rest/v1/elevator/all endpoint to get the status of all Elevators.

# Deploy
* pom.xml is configured with docker-maven-plugin and it lets you build an image using maven goal 

* The Dockerfile is present in src/main/docker folder and uses java:8 as base image.

* Run the following command to deploy the artifact to a docker host
mvn package docker:build

* Using docker deamon you can list the images
docker images

* Run the container using image name or id. replace the right port with one configured in application.properties 
docker run -p 8880:8880 -t tingcore/elevator
