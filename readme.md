
SafetyNet-Alerts    V1.0
========================
[![forthebadge](https://forthebadge.com/images/badges/made-with-java.svg)](https://forthebadge.com)    [![forthebadge](https://forthebadge.com/images/badges/uses-git.svg)](https://forthebadge.com) 
     
## Info

SafetyNet-Alerts is a back-end application developed using Spring Boot in which we exploit data from a Json file to respond to specific Urls for both data management and operations, all this for the emergency intervention in an area.

Objective: Create your first web application with Spring Boot.
------------
In this first Spring Boot approach, we are therefore led to develop the back-end which will feed back the information taken from the Json file, through Urls that we use with Postman (https://www.postman.com/) for more of facilities.

Technologies
------------
* Java 1.8
* Maven
* Spring Boot 
* Junit
* JaCoCo
* Log4j2

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
	* ex: http://localhost:8080/firestation/2
	
 * **http ://localhost:8080/childAlert?address=<address>**
	* ex: http://localhost:8080/childAlert/1509 Culver St
	
 * **http ://localhost:8080/phoneAlert?firestation=<firestation_number**
	* ex: http://localhost:8080/phoneAlert/3

 * **http ://localhost:8080/fire?address=<address>**
	* ex: http://localhost:8080/fire/1509 Culver St
	
 * **http ://localhost:8080/flood/stations?stations=<a list of station_numbers>**
	* ex: http://localhost:8080/flood/stations/1
	
 * **http ://localhost:8080/personInfo?firstName=<firstName>&lastName=<lastName>**
	* ex: http://localhost:8080/personInfo/John Boyd
	
 * **http ://localhost:8080/communityEmail?city=<city>**
	* ex: http://localhost:8080/communityEmail/Culver

## Authors

Laurent Y. DA Java student OpenClassRooms 
	support by his Mentor Mathieu
