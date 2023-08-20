package com.frotas.api.repository;

import com.frotas.api.model.Maintenance;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomMaintenanceRepository {

    Maintenance saveMaintenanceByIdVehicle(Maintenance maintenance, Integer id);
}
