This is a Spring Boot application written in Java. The application comprises a Spring MVC web interface and a RESTful web service. The source code of this application can be found in AutomobileMaintenanceTracking.zip

To execute application, follow the instruction below;

-	Download executable jar file ‘AutomobileMaintenanceTracking-0.0.1-SNAPSHOT.jar’ into a directory in your computer
-	Open up a command line (Windows users) or terminal (Mac users) and go into the directory where you downloaded the jar
-	Type in following command and hit enter
    	java -jar  AutomobileMaintenanceTracking-0.0.1-SNAPSHOT.jar
-	In the console if you see ‘Started AutomobileMaintenanceTrackingApplication in xxx seconds’ then the application is started successfully and ready to use

Sample output ==>
2018-08-03 09:09:22.555  INFO 94901 --- [           main] o.s.j.e.a.AnnotationMBeanExporter        : Registering beans for JMX exposure on startup
2018-08-03 09:09:22.599  INFO 94901 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2018-08-03 09:09:22.607  INFO 94901 --- [           main] AutomobileMaintenanceTrackingApplication : Started AutomobileMaintenanceTrackingApplication in 2.843 seconds (JVM running for 3.441)

Usage instructions
~~~~~~~~~~~~~~~~~~
•	To use the Spring MVC web interface:
Go to your favorite browser and type in url - http://localhost:8080/vehicles

•	To use the RESTful web service:
    - Add operation:  http://localhost:8080/api/addVehicle
        Here is a sample payload;
                { 
                    "make": "Acura”, 
                    "model": "LX”,
                    "year": 2014, 
                    "odometer": 3500000,
                     "carType": "Gas” 
                  }


    - List operation:  http://localhost:8080/api/vehicles

    - Update operation - http://localhost:8080/api/updateVehicle
        Here is a sample payload;

        {
        "vehicleId": 1,
        "make": "Acura",
        "model": "LXT2",
        "year": 2014,
        "odometer": 3500000,
        "carType": "Gas",
        "serviceDate": "08-19-2018 15:30"
        }

    - Delete operation - http://localhost:8080/api/deleteVehicle/{vehicleId}

        Here is a sample request to remove a vehicle entry with vehicle id 1;
        http://localhost:8080/api/deleteVehicle/1


