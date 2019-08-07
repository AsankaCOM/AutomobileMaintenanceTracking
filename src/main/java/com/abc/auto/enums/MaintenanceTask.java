package com.abc.auto.enums;

public enum MaintenanceTask {

	OilChange("Oil change"),
	TireRotation("Tire rotation"),
	TopUpFluids("Top-up the fluids"),
	BatteryCheck("Monitor the battery");

	private String task;

	MaintenanceTask(String task){
		this.task = task;
	}

	public String toString()
	{
		return task;
	}
}
