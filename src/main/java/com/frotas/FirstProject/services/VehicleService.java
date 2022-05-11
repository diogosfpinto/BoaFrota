package com.frotas.FirstProject.services;

import com.frotas.FirstProject.model.User;
import com.frotas.FirstProject.model.Vehicle;
import com.frotas.FirstProject.repository.UserRepository;
import com.frotas.FirstProject.repository.VehicleRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private UserRepository userRepository;

    /**
     * Endpoint para cadastrar novo veículo
     * @param vehicle Objeto veículo
     **/
    public Vehicle addNewVehicle(Vehicle vehicle){
        ArrayList<User> users = new ArrayList<>();
        users.add(getUserAuthenticated());
        vehicle.setUsers(users);
        return vehicleRepository.save(vehicle);
    }

    public Iterable<Vehicle> getAllVehicles(){
        return vehicleRepository.findAll();
    }

    public ResponseEntity<Vehicle> getVehicleById(Integer id){
        return vehicleRepository.findById(id)
                .map(vehicle -> ResponseEntity.ok().body(vehicle))
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Vehicle> updateVehicleById(Integer id, Vehicle vehicle){
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

    public ResponseEntity<Object> deleteVehiclebyId(Integer id){
        return vehicleRepository.findById(id)
                .map(vehicleToDelete ->{
                    vehicleRepository.deleteById(id);
                    return ResponseEntity.noContent().build();
                }).orElse(ResponseEntity.notFound().build());
    }

    private User getUserAuthenticated(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){

            return (User) authentication.getPrincipal();
        }
        return new User();
    }

   public List<User> getUsersByVehicleId(Integer id) {

        return vehicleRepository.findById(id).map(vehicle -> vehicle.getUsers())
           .orElse(new ArrayList<>());
   }
}
