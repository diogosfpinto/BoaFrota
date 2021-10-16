package com.frotas.FirstProject.controller;

import com.frotas.FirstProject.model.Vehicle;
import com.frotas.FirstProject.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/demo")
public class VehicleController {

    @Autowired
    private VehicleRepository vehicleRepository;

    @PostMapping(path="/add")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody String addNewVehicle(@RequestBody Vehicle vehicle){

        vehicleRepository.save(vehicle);
        return "saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Vehicle> getAllVehicles(){
        return vehicleRepository.findAll();
    }

}