package com.frotas.FirstProject.controller;

import com.frotas.FirstProject.model.Vehicle;
import com.frotas.FirstProject.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping(path="/vehicle")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody String addNewVehicle(@RequestBody Vehicle vehicle){

        vehicleService.addNewVehicle(vehicle);
        return "saved";
    }

    @GetMapping(path="/vehicles")
    public @ResponseBody Iterable<Vehicle> getAllVehicles(){
        return vehicleService.getAllVehicles();
    }

    @GetMapping(path = "/vehicle/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable (value = "id") Integer id){
        return vehicleService.getVehicleById(id);
    }

    @PutMapping("/vehicle/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Vehicle> updateVehicleById(@PathVariable (value = "id") Integer id,
                                                     @RequestBody Vehicle vehicle){
        return vehicleService.updateVehicleById(id, vehicle);
    }

    @DeleteMapping("/vehicle/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> deleteVehiclebyId(@PathVariable (value = "id") Integer id){
        return vehicleService.deleteVehiclebyId(id);
    }
}
