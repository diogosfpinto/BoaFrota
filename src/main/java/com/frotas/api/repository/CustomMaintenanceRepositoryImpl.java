package com.frotas.api.repository;

import com.frotas.api.model.Maintenance;
import com.frotas.api.model.Vehicle;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

public class CustomMaintenanceRepositoryImpl implements CustomMaintenanceRepository{


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Maintenance saveMaintenanceByIdVehicle(Maintenance maintenance, Integer id) {

        Vehicle vehicle = new Vehicle();

        vehicle.setId(id);
        maintenance.setVehicle(vehicle);
        entityManager.persist(maintenance);
        return maintenance;
    }


}
