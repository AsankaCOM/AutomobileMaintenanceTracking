package com.abc.auto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.abc.auto.model.Vehicle;


@Service
public class MaintenanceService {
	
	private static Long vehicleId = 0L;

	protected Map<Long,Vehicle> vehicleResository = new HashMap<>();

	public List<Vehicle> getAllVehicles(){
		List<Vehicle> list = new ArrayList<>();
				
		Iterator it = vehicleResository.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        list.add((Vehicle) pair.getValue());
	    }
	    
	    return list;
		
	}
	
	public Long addVehicle(Vehicle vehicle){
		vehicleId += 1;
		vehicle.setVehicleId(vehicleId);
		this.vehicleResository.put(vehicle.getVehicleId(), vehicle);
		return vehicleId;
	}	

	public void removeVehicle(Long vehicleId){
		if (vehicleId == null)
			return;
		this.vehicleResository.remove(vehicleId);
	}
	
	public void updateVehicle(Vehicle vehicle){
		this.vehicleResository.put(vehicle.getVehicleId(), vehicle);
	}
	
	public Vehicle getVehicleById(Long vehicleId){
		if (vehicleId == null)
			return null;
		return this.vehicleResository.get(vehicleId);
	}		
}
