package com.frotas.FirstProject.repository;

import com.frotas.FirstProject.model.Maintenance;

public interface CustomMaintenanceRepository {

    String saveMaintenanceByIdVehicle(Maintenance maintenance, Integer id);
}
