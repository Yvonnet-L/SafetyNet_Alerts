
SafetyNet-Alerts    V1.0
========================
[![forthebadge](https://forthebadge.com/images/badges/made-with-java.svg)](https://forthebadge.com)    [![forthebadge](https://forthebadge.com/images/badges/uses-git.svg)](https://forthebadge.com) 
     
## Info

SafetyNet-Alerts is a back-end application developed using Spring Boot in which we exploit data from a Json file to respond to specific Urls for both data management and operations, all this for the emergency intervention in an area.

Objective: Create your first web application with Spring Boot.
------------
In this first Spring Boot approach, we are therefore led to develop the back-end which will feed back the information taken from the Json file, through Urls that we use with Postman (https://www.postman.com/) for more of facilities.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

## Prerequisites
### Technologies

* Java 1.8
* Maven 4.0.0
* Spring Boot 2.4.3

### Installing

Here are the links for the prerequisites necessary for proper operation. In addition, I invite you to download postman to test the urls of the application.

1.Install Java:
	* https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html

2.Install Maven:
	* https://maven.apache.org/install.html

3.Install Postman
	* https://www.postman.com/downloads/

## Running App

After downloading and installing, you'll finally be ready to import the code into an IDE of your choice and run App.java to launch the app.

To run the tests from maven, go to the folder that contains the pom.xml file and execute the below command.

`mvn test`

## API with Data File Json

* **URL:**	http://localhost:8080/

* **File json:**	src/main/ressources/data/data.json

## EndPoints
### POST / PUT / DELETE

* http://localhost:8080/firestation
* http://localhost:8080/medicalrecord
* http://localhost:8080/person

### GET

* http://localhost:8080/firestations
* http://localhost:8080/medicalrecords
* http://localhost:8080/persons

### GET for emergency response

 * **http ://localhost:8080/firestation?stationNumber=<station_number>**
	* ex: http://localhost:8080/firestation?stationNumber=3
'{
    "nbAdult": 5,
    "nbChild": 3,
    "person": [
        {
            "firstName": "John",
            "lastName": "Boyd",
            "address": "1509 Culver St",
            "phone": "841-874-6512"
        },
        {
            "firstName": "Jacob",
            "lastName": "Boyd",
            "address": "1509 Culver St",
            "phone": "841-874-6513"
        },
        {
            "firstName": "Tenley",
            "lastName": "Boyd",
            "address": "1509 Culver St",
            "phone": "841-874-6512"
        },
        {
            "firstName": "Roger",
            "lastName": "Boyd",
            "address": "1509 Culver St",
            "phone": "841-874-6512"
        },
        {
            "firstName": "Felicia",
            "lastName": "Boyd",
            "address": "1509 Culver St",
            "phone": "841-874-6544"
        },
        {
            "firstName": "Tessa",
            "lastName": "Carman",
            "address": "834 Binoc Ave",
            "phone": "841-874-6512"
        },
        {
            "firstName": "Foster",
            "lastName": "Shepard",
            "address": "748 Townings Dr",
            "phone": "841-874-6544"
        },
        {
            "firstName": "Clive",
            "lastName": "Ferguson",
            "address": "748 Townings Dr",
            "phone": "841-874-6741"
        }
    ]
}'
	
 * **http ://localhost:8080/childAlert?address=<address>**
	* ex: http://localhost:8080/childAlert?address=1509 Culver St
	
 * **http ://localhost:8080/phoneAlert?firestation=<firestation_number**
	* ex: http://localhost:8080/phoneAlert?firestation=3

 * **http ://localhost:8080/fire?address=<address>**
	* ex: http://localhost:8080/fire?address=1509 Culver St
	
 * **http ://localhost:8080/flood/stations?stations=<a list of station_numbers>**
	* ex: http://localhost:8080/flood/stations?stations=3
	
 * **http ://localhost:8080/personInfo?firstName=<firstName>&lastName=<lastName>**
	* ex: http://localhost:8080/personInfo?firstName=John&lastName=Boyd
	
 * **http ://localhost:8080/communityEmail?city=<city>**
	* ex: http://localhost:8080/communityEmail?city=Culver

## Authors

Laurent Y. DA Java student OpenClassRooms 
	support by his Mentor Mathieu
