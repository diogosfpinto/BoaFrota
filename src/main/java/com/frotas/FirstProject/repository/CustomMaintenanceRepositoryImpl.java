package com.frotas.FirstProject.repository;

import com.frotas.FirstProject.model.Maintenance;
import com.frotas.FirstProject.model.Vehicle;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

public class CustomMaintenanceRepositoryImpl implements CustomMaintenanceRepository{


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public String saveMaintenanceByIdVehicle(Maintenance maintenance, Integer id) {

        Vehicle vehicle = new Vehicle();

        vehicle.setId(id);
        maintenance.setVehicle(vehicle);
        entityManager.persist(maintenance);
        return "ID do veículo: " + id;
    }


}
