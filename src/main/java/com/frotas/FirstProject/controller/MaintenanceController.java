package com.frotas.FirstProject.controller;

import com.frotas.FirstProject.model.Maintenance;
import com.frotas.FirstProject.repository.MaintenanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path="/vehicles/{id}")
public class MaintenanceController {

    @Autowired
    private MaintenanceRepository maintenanceRepository;

    @PostMapping("/maintenance")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody String addNewMaintenance(@PathVariable (value = "id") Integer id,
                                                  @RequestBody Maintenance maintenance){

        return maintenanceRepository.saveMaintenanceByIdVehicle(maintenance, id);
    }

    //        Como buscar as manutenções de um veículo em específico
    @GetMapping(path="/maintenances")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<Maintenance> getAllMaintenancesByIdVehicle
                                            (@PathVariable (value = "id") Integer id){
        return maintenanceRepository.findByVehicleId(id);
    }

}
