package com.frotas.FirstProject.controller;

import com.frotas.FirstProject.model.Vehicle;
import com.frotas.FirstProject.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/vehicles")
public class VehicleController {

    @Autowired
    private VehicleRepository vehicleRepository;

    @PostMapping(path="/add")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody String addNewVehicle(@RequestBody Vehicle vehicle){

        vehicleRepository.save(vehicle);
        return "saved";
    }

    @GetMapping(path="/")
    public @ResponseBody Iterable<Vehicle> getAllVehicles(){
        return vehicleRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable (value = "id") Integer id){
        return vehicleRepository.findById(id)
                .map(vehicle -> ResponseEntity.ok().body(vehicle))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Vehicle> updateVehicleById(@PathVariable (value = "id") Integer id,
                                                     @RequestBody Vehicle vehicle){
        return vehicleRepository.findById(id)
                .map(vehicleToUpdate -> {
                    vehicleToUpdate.setYear(vehicle.getYear());
                    vehicleToUpdate.setModel(vehicle.getModel());
                    vehicleToUpdate.setBrand(vehicle.getBrand());
                    vehicleToUpdate.setColor(vehicle.getColor());
                    vehicleToUpdate.setPlate(vehicle.getPlate());
                    vehicleToUpdate.setRenavam(vehicle.getRenavam());
                    Vehicle updated = vehicleRepository.save(vehicleToUpdate);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }


}
