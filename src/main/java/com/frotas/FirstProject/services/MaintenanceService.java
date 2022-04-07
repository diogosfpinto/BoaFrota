package com.frotas.FirstProject.services;

import com.frotas.FirstProject.model.Maintenance;
import com.frotas.FirstProject.repository.MaintenanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
}
