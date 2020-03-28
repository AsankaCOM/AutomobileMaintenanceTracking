package com.abc.auto;

import java.util.Arrays;
import java.util.List;

import com.abc.auto.enums.CarType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.abc.auto.model.Vehicle;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;

@Controller
@RequestMapping("/vehicles")
public class MaintenanceController implements WebMvcConfigurer {
	
	private static final Logger logger = LoggerFactory.getLogger(MaintenanceController.class);
	
	private MaintenanceService service;
	
	@Autowired
	public MaintenanceController(MaintenanceService reservationService)
	{
		super();
		this.service = reservationService;
	}
	
	@GetMapping()
	public ModelAndView home(Model model) {
		return new ModelAndView("redirect:/vehicles/viewVehicles");
	}

	@GetMapping("/")
	@ResponseBody
	public String home(){
		return "Howdy world !!";
	}

	@GetMapping("/addVehicles")
	public String showform(Model model){
		model.addAttribute("vehicle", new Vehicle());

		List<CarType> enums = Arrays.asList(CarType.values());
		model.addAttribute("enums",enums);

		return "addVehicles";
	}
	
	@PostMapping(value="/addVehicles")
	public ModelAndView save(@Valid @ModelAttribute("vehicle") Vehicle vehicle, BindingResult bindingResult){
		if (bindingResult.hasErrors()) {
			return new ModelAndView("addVehicles");
		}

		this.service.addVehicle(vehicle);
		return new ModelAndView("redirect:/vehicles/viewVehicles");
	}


	@GetMapping("/viewVehicles")
	public String viewVehicles(Model model) {
		List<Vehicle> list = this.service.getAllVehicles();

		model.addAttribute("list", list);
		return "viewVehicles";
	}
	
	@GetMapping(value="/editVehicles/{vehicleId}")
	public String edit(@PathVariable("vehicleId") long vehicleId, Model model){ Vehicle vehicle = this.service.getVehicleById(vehicleId);
		model.addAttribute("vehicle", vehicle);
		return "editVehicles";
	}
	
	@PostMapping(value="/editVehicles/editAndsave")
	public ModelAndView editsave(@Valid @ModelAttribute("vehicle") Vehicle vehicle, BindingResult bindingResult){

		if (bindingResult.hasErrors()) {
			if (!(bindingResult.getErrorCount() == 1
					 && bindingResult.getFieldError("serviceDate") != null
					 && "".equals(bindingResult.getFieldError("serviceDate").getRejectedValue() )))
			{
				return new ModelAndView("editVehicles");
			}
		}

		this.service.updateVehicle(vehicle);
		return new ModelAndView("redirect:/vehicles/viewVehicles");
	}

	@GetMapping(value="/serviceVehicles/{vehicleId}")
	public String serviceInfo(@PathVariable("vehicleId") long vehicleId, Model model){

		Vehicle vehicle = this.service.getVehicleById(vehicleId);

		model.addAttribute("vehicle", vehicle);

		model.addAttribute("list", vehicle.service());

		return "serviceVehicles";
	}

	@PostMapping(value="/serviceVehicles/editAndsave")
	public ModelAndView serviceInfoSave(@ModelAttribute("vehicle") Vehicle vehicle, BindingResult bindingResult){

		if (bindingResult.hasErrors()) {
			if (!(bindingResult.getErrorCount() == 1
					&& bindingResult.getFieldError("serviceDate") != null
					&& "".equals(bindingResult.getFieldError("serviceDate").getRejectedValue() )))
			{
				return new ModelAndView("editVehicles");
			}
		}

		this.service.updateVehicle(vehicle);
		return new ModelAndView("redirect:/vehicles/viewVehicles");
	}

	@GetMapping(value="/deleteVehicles/{vehicleId}")
	public ModelAndView delete(@PathVariable("vehicleId") Long vehicleId){
		this.service.removeVehicle(vehicleId);
		return new ModelAndView("redirect:../viewVehicles");
	}

}
