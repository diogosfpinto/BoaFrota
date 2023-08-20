package com.frotas.api.controller;

import com.frotas.api.model.Maintenance;
import com.frotas.api.services.MaintenanceService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path="/vehicles/{id}")
public class MaintenanceController {

    @Autowired
    private MaintenanceService maintenanceService;

    @ApiOperation("Inserindo uma manutenção para o veículo selecionado")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Manutenção inserida com sucesso"),
            @ApiResponse(code = 500,
                    message = "Houve um erro ao inserir a manutenção, verifique as informações.")
    })
    @PostMapping("/maintenance")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Maintenance addNewMaintenance(@PathVariable (value = "id") Integer id,
                                                  @RequestBody Maintenance maintenance){

        return maintenanceService.addNewMaintenance(maintenance, id);
    }

    //        Como buscar as manutenções de um veículo em específico
    @ApiOperation("Retornando todos as manutenções para o veículo selecionado")
    @GetMapping(path="/maintenances")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<Maintenance> getAllMaintenancesByIdVehicle
                                            (@PathVariable (value = "id") Integer id){
        return maintenanceService.getAllMaintenancesByIdVehicle(id);
    }

    @ApiOperation("Atualizando dados de manutenção por ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Manutenção atualizada com sucesso."),
            @ApiResponse(code = 500,
                    message = "Houve um erro ao atualizar o registro de manutenção, verifique as informações.")
    })
    @PutMapping("/maintenance/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Maintenance> updateMaintenanceById(@PathVariable (value = "id") Integer id,
                                                     @RequestBody Maintenance maintenance){
        return maintenanceService.updateMaintenanceById(id, maintenance);
    }

    @ApiOperation("Excluindo manutenção do sistema.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Registro de manutenção excluído com sucesso."),
            @ApiResponse(code = 500,
                    message = "Houve um erro ao excluir a manuntenção, verifique as informações.")
    })
    @DeleteMapping("/maintenance/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> deleteMaintenanceById(@PathVariable (value = "id") Integer id){
        return maintenanceService.deleteMaintenanceById(id);
    }
}
