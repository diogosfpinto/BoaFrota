package com.frotas.FirstProject.controller;

import com.frotas.FirstProject.model.Maintenance;
import com.frotas.FirstProject.repository.MaintenanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/vehicles/{id}/maintenances")
public class MaintenanceController {

    @Autowired
    private MaintenanceRepository maintenanceRepository;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody String addNewMaintenance(@PathVariable (value = "id") Integer id,
                                                  @RequestBody Maintenance maintenance){

        return maintenanceRepository.saveMaintenanceByIdVehicle(maintenance, id);
    }

    //        Como buscar as manutenções de um veículo em específico
/*    @GetMapping(path="/")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Maintenance> getAllMaintenances(){
        return maintenanceRepository.findAll();
    }*/

}
