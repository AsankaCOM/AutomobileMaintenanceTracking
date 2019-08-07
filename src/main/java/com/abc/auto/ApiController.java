package com.abc.auto;
import java.util.List;

import com.abc.auto.enums.CarType;
import com.abc.auto.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiController {

    private MaintenanceService reservationService;

    @Autowired
    public ApiController(MaintenanceService reservationService)
    {
        super();
        this.reservationService = reservationService;
    }

    @GetMapping()
    public String home()
    {
        return "Automobile Maintenance Tracking API";
    }

    @GetMapping("/vehicles")
    public List<Vehicle> getAllVehicles()
    {
        return this.reservationService.getAllVehicles();
    }

    @PostMapping(value="/addVehicle")
    public String addVehicle(@RequestBody Vehicle vehicle){

        if (this.reservationService.getVehicleById(vehicle.getVehicleId()) != null){
            return "Vahicle id-" +  vehicle.getVehicleId() + " already exist";
        }

        return "Vehicle added, id-" +  this.reservationService.addVehicle(vehicle);
    }

    @GetMapping(value="/deleteVehicle/{vehicleId}")
    public String deleteVehicle(@PathVariable("vehicleId") long vehicleId){

        if (this.reservationService.getVehicleById(vehicleId) == null){
            return "Invalid vehicle id: " +  vehicleId;
        }

        this.reservationService.removeVehicle(vehicleId);

        return "Vehicle entry removed";
    }

    @PostMapping(value="/updateVehicle")
    public String updateVehicle(@RequestBody Vehicle vehicle){

        if (vehicle.getVehicleId() == null || this.reservationService.getVehicleById(vehicle.getVehicleId()) == null){
            return "Invalid vehicle id: " +  vehicle.getVehicleId();
        }

        this.reservationService.updateVehicle(vehicle);
        return "Vehicle updated";
    }

    @GetMapping(value="/carTypes")
    public CarType[] displayCarTypes(){
        return CarType.values();
    }

}
