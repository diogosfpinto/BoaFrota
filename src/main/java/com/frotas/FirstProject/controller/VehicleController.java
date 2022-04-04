package com.frotas.FirstProject.controller;

import com.frotas.FirstProject.model.Vehicle;
import com.frotas.FirstProject.services.VehicleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @ApiOperation("Adicionando novo veículo no sistema.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Veículo adicionado com sucesso."),
            @ApiResponse(code = 500,
                    message = "Houve um erro ao adicionar novo veículo, verifique as informações.")
    })
    @PostMapping(path="/vehicle")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody String addNewVehicle(@RequestBody Vehicle vehicle){

        vehicleService.addNewVehicle(vehicle);
        return "saved";
    }

    @ApiOperation("Retornando todos os veículos do sistema.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Veículos retornados com sucesso."),
            @ApiResponse(code = 500,
                    message = "Houve um erro ao retornar todos os veículos, verifique as informações.")
    })
    @GetMapping(path="/vehicles")
    public @ResponseBody Iterable<Vehicle> getAllVehicles(){
        return vehicleService.getAllVehicles();
    }

    @ApiOperation("Retornando veículo por ID informado.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Veículo retornado com sucesso."),
            @ApiResponse(code = 500,
                    message = "Houve um erro ao retornar veículo por ID, verifique as informações.")
    })
    @GetMapping(path = "/vehicle/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable (value = "id") Integer id){
        return vehicleService.getVehicleById(id);
    }

    @ApiOperation("Atualizando dados de veículo por ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Veículo atualizado com sucesso."),
            @ApiResponse(code = 500,
                    message = "Houve um erro ao atualizar veículo, verifique as informações.")
    })
    @PutMapping("/vehicle/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Vehicle> updateVehicleById(@PathVariable (value = "id") Integer id,
                                                     @RequestBody Vehicle vehicle){
        return vehicleService.updateVehicleById(id, vehicle);
    }

    @ApiOperation("Excluindo veículo do sistema.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Veículo excluído com sucesso."),
            @ApiResponse(code = 500,
                    message = "Houve um erro ao excluir veículo, verifique as informações.")
    })
    @DeleteMapping("/vehicle/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> deleteVehiclebyId(@PathVariable (value = "id") Integer id){
        return vehicleService.deleteVehiclebyId(id);
    }
}
