package com.frotas.FirstProject.controller;

import com.frotas.FirstProject.model.Maintenance;
import com.frotas.FirstProject.repository.MaintenanceRepository;
import com.frotas.FirstProject.repository.VehicleRepository;
import org.jboss.jandex.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/maintenances")
public class MaintenanceController {

    @Autowired
    private MaintenanceRepository maintenanceRepository;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody String addNewMaintenance(@RequestBody Maintenance maintenance){
    // Como salvar a manutenção juntamente com a chave estrangeira do veiculo em que está relacionado?
        maintenanceRepository.save(maintenance);
        return "saved";
    }
/*
    @GetMapping(path="/{id_vehicle}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Maintenance> getAllMaintenances(@PathVariable (value = "id_vehicle")
                                                                  Integer id){
//        Como buscar as manutenções de um veículo em específico
        return maintenanceRepository.findById(id)
                .map(maintenance -> ResponseEntity.ok().body(maintenance))
                .orElse(ResponseEntity.notFound().build());
    }*/

}
