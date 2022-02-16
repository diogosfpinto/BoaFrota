package com.frotas.FirstProject.controller;

import com.frotas.FirstProject.model.Maintenance;
import com.frotas.FirstProject.repository.MaintenanceRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @ApiOperation("Inserindo uma manutenção para o veículo selecionado")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Manutenção inserida com sucesso"),
            @ApiResponse(code = 500,
                    message = "Houve um erro ao inserir a manutenção, verifique as informações.")
    })
    @PostMapping("/maintenance")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody String addNewMaintenance(@PathVariable (value = "id") Integer id,
                                                  @RequestBody Maintenance maintenance){

        return maintenanceRepository.saveMaintenanceByIdVehicle(maintenance, id);
    }

    //        Como buscar as manutenções de um veículo em específico
    @ApiOperation("Retornando todos as manutenções para o veículo selecionado")
    @GetMapping(path="/maintenances")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<Maintenance> getAllMaintenancesByIdVehicle
                                            (@PathVariable (value = "id") Integer id){
        return maintenanceRepository.findByVehicleId(id);
    }

}
