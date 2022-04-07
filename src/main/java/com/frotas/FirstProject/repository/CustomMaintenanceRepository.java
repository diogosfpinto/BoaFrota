package com.frotas.FirstProject.repository;

import com.frotas.FirstProject.model.Maintenance;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomMaintenanceRepository {

    Maintenance saveMaintenanceByIdVehicle(Maintenance maintenance, Integer id);
}
