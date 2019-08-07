package com.abc.auto.model;

import com.abc.auto.enums.CarType;
import com.abc.auto.enums.MaintenanceTask;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class Vehicle {

	private Long vehicleId ;

	@NotEmpty
	private String make;

	@NotEmpty
	private String model;

	@NotNull
	@Min(1900)
	@Max(2030)
	private Integer year;

	@NotNull
	private Long odometer;

	private CarType carType;

	@JsonFormat(pattern="MM-dd-yyyy HH:mm")
	private Date serviceDate;

	public Date getServiceDate() {
		return serviceDate;
	}

	public void setServiceDate(Date serviceDate) {
		this.serviceDate = serviceDate;
	}

	public Long getVehicleId() {
		return vehicleId;
	}
	
	public void setVehicleId(Long id){
		vehicleId = id;
	}
	
	public String getMake() {
		return make;
	}
	
	public void setMake(String make) {
		this.make = make;
	}
	
	public String getModel(){
		return model;
	}
	
	public void setModel (String model){
		this.model = model;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Long getOdometer() {
		return odometer;
	}

	public void setOdometer(Long odometer) {
		this.odometer = odometer;
	}

	public CarType getCarType() {
		return carType;
	}

	public void setCarType(CarType carType) {
		this.carType = carType;
	}
	
	public MaintenanceTask[] service() {
		return carType.getMaintenanceTasks();
	}

}
