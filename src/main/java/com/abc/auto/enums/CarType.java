package com.abc.auto.enums;

public enum CarType {

	Gas(new MaintenanceTask[]{ MaintenanceTask.OilChange, MaintenanceTask.TireRotation, MaintenanceTask.BatteryCheck}),
	Diesal(new MaintenanceTask[]{ MaintenanceTask.OilChange, MaintenanceTask.TireRotation, MaintenanceTask.BatteryCheck}),
	Electric(new MaintenanceTask[]{MaintenanceTask.BatteryCheck, MaintenanceTask.TireRotation});

	private MaintenanceTask[] maintenanceTasks;

	CarType(MaintenanceTask[] maintenanceTasks){
		this.maintenanceTasks = maintenanceTasks;
	}

	public MaintenanceTask[] getMaintenanceTasks()
	{
		return maintenanceTasks;
	}
}
