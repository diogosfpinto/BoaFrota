package com.frotas.api.services;

import com.frotas.api.model.Maintenance;
import com.frotas.api.repository.MaintenanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaintenanceService {

    @Autowired
    private MaintenanceRepository maintenanceRepository;

    public Maintenance addNewMaintenance(Maintenance maintenance, Integer id){
        return maintenanceRepository.saveMaintenanceByIdVehicle(maintenance, id);
    }

    public List<Maintenance> getAllMaintenancesByIdVehicle(Integer id){
        return maintenanceRepository.findByVehicleId(id);
    }

    public ResponseEntity<Maintenance> updateMaintenanceById(Integer id, Maintenance maintenance){
        return maintenanceRepository.findById(id)
                .map(maintenanceToUpdate -> {
                    maintenanceToUpdate.setDateMaintenance(maintenance.getDateMaintenance());
                    maintenanceToUpdate.setTypeMaintenance(maintenance.getTypeMaintenance());
                    maintenanceToUpdate.setCategory(maintenance.getCategory());
                    maintenanceToUpdate.setComments(maintenance.getComments());
                    maintenanceToUpdate.setCost(maintenance.getCost());
                    maintenanceToUpdate.setKm(maintenance.getKm());
                    Maintenance updated = maintenanceRepository.save(maintenanceToUpdate);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Object> deleteMaintenanceById(Integer id){
        return maintenanceRepository.findById(id)
                .map(maintenanceToDelete ->{
                    maintenanceRepository.deleteById(id);
                    return ResponseEntity.noContent().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
