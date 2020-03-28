package com.abc.auto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.*;

import com.abc.auto.enums.CarType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.abc.auto.model.Vehicle;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AutomobileMaintenanceTrackingApplicationTests {

	@Test
	public void testAddVehicles_and_list_them_all() {
		
		MaintenanceService service = new MaintenanceService();
		
		//vehicle 1
		Vehicle vehicle1 = new Vehicle();
		vehicle1.setMake("Acura");
		vehicle1.setYear(2018);
		vehicle1.setModel("LX");
		vehicle1.setOdometer(12500L);
		vehicle1.setCarType(CarType.Electric);

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DATE, 30);
		cal.set(Calendar.MONTH, Calendar.JULY);
		cal.set(Calendar.YEAR, 2018);
		cal.set(Calendar.HOUR_OF_DAY,17);
		cal.set(Calendar.MINUTE,30);
		cal.set(Calendar.SECOND,0);
		cal.set(Calendar.MILLISECOND,0);

		Date serviceDate= cal.getTime();
		vehicle1.setServiceDate(serviceDate);

		//vehicle 2
		Vehicle vehicle2 = new Vehicle();
		vehicle2.setMake("Honda");
		vehicle2.setYear(2010);
		vehicle2.setModel("Accord");
		vehicle2.setOdometer(120000L);
		vehicle2.setCarType(CarType.Gas);

		service.addVehicle(vehicle1);
		service.addVehicle(vehicle2);

		List<Vehicle> vehicles = service.getAllVehicles();
		assertEquals("The vehicle list size should be 2",2, vehicles.size());

		assertEquals("Acura", vehicles.get(0).getMake());
		assertEquals(new Integer(2018), vehicles.get(0).getYear());
		assertEquals("LX", vehicles.get(0).getModel());
		assertEquals(new Long(12500), vehicles.get(0).getOdometer());
		assertEquals("Mon Jul 30 17:30:00 UTC 2018", vehicles.get(0).getServiceDate().toString());
		assertEquals(CarType.Electric, vehicles.get(0).getCarType());

		assertEquals("Honda", vehicles.get(1).getMake());
		assertEquals(new Integer(2010), vehicles.get(1).getYear());
		assertEquals("Accord", vehicles.get(1).getModel());
		assertEquals(new Long(120000), vehicles.get(1).getOdometer());
		assertNull("", vehicles.get(1).getServiceDate());
		assertEquals(CarType.Gas, vehicles.get(1).getCarType());

	}

	@Test
	public void testUpdateVehicles() {

		MaintenanceService service = new MaintenanceService();

		Vehicle vehicle1 = new Vehicle();
		vehicle1.setMake("Acura");
		vehicle1.setYear(2018);
		vehicle1.setModel("LX");
		vehicle1.setOdometer(1500000L);

		service.addVehicle(vehicle1);

		Vehicle vehicle2 = new Vehicle();
		vehicle2.setMake("Honda");
		vehicle2.setYear(2010);
		vehicle2.setModel("SE");
		vehicle2.setOdometer(120000L);

		service.addVehicle(vehicle2);

		List<Vehicle> vehicles = service.getAllVehicles();
		assertEquals("The vehicle list size should be 2",2, vehicles.size());
		assertEquals("Should return updated model","SE", vehicles.get(1).getModel());
		assertEquals("Should return updated odometer reading",new Long(120000), vehicles.get(1).getOdometer());


		Vehicle vehicle2_update = new Vehicle();
		vehicle2_update.setVehicleId(vehicle2.getVehicleId());
		vehicle2_update.setMake(vehicle2.getMake());
		vehicle2_update.setYear(vehicle2.getYear());
		vehicle2_update.setModel("LX");
		vehicle2_update.setOdometer(300000L);

		//update vehicle...
		service.updateVehicle(vehicle2_update);

		vehicles = service.getAllVehicles();
		assertEquals("The vehicle list size should be 2",2, vehicles.size());
		assertEquals("Should return updated model","LX", vehicles.get(1).getModel());
		assertEquals("Should return updated odometer reading",new Long(300000), vehicles.get(1).getOdometer());

	}

	@Test
	public void testRemoveVehicles() {

		MaintenanceService service = new MaintenanceService();

		Vehicle vehicle1 = new Vehicle();
		vehicle1.setMake("Acura");
		vehicle1.setYear(2018);
		vehicle1.setModel("LX");
		vehicle1.setOdometer(1500000L);

		Vehicle vehicle2 = new Vehicle();
		vehicle2.setMake("Honda");
		vehicle2.setYear(2010);
		vehicle2.setModel("Accord");
		vehicle2.setOdometer(120000L);

		Vehicle vehicle3 = new Vehicle();
		vehicle3.setMake("Subaru");
		vehicle3.setYear(2010);
		vehicle3.setModel("Outback");
		vehicle3.setOdometer(45000L);

		service.addVehicle(vehicle1);
		service.addVehicle(vehicle2);
		service.addVehicle(vehicle3);

		List<Vehicle> vehicles = service.getAllVehicles();
		assertEquals("The vehicle list size should be 3",3, vehicles.size());

		//remove a vehicle...
		long vehicleToRemove = vehicle2.getVehicleId();
		service.removeVehicle(vehicleToRemove);

		vehicles = service.getAllVehicles();
		assertEquals("The vehicle list size should be 2",2, vehicles.size());
		assertNull("The vehicle with id-" + vehicleToRemove + " should have been removed", service.getVehicleById(vehicleToRemove));
		assertNotNull("The vehicle with id-" + vehicle1.getVehicleId() + " could not find", service.getVehicleById(vehicle1.getVehicleId()));
		assertEquals("Should return updated model","LX", service.getVehicleById(vehicle1.getVehicleId()).getModel());
		assertEquals("Should return updated odometer reading",new Long(1500000), service.getVehicleById(vehicle1.getVehicleId()).getOdometer());
		assertNotNull("The vehicle with id-" + vehicle3.getVehicleId() + " could not find", service.getVehicleById(vehicle3.getVehicleId()));
		assertEquals("Should return updated model","Outback", service.getVehicleById(vehicle3.getVehicleId()).getModel());
		assertEquals("Should return updated odometer reading",new Long(45000), service.getVehicleById(vehicle3.getVehicleId()).getOdometer());

	}
}
