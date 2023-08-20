package com.frotas.api.repository;

import com.frotas.api.model.Maintenance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaintenanceRepository extends CrudRepository<Maintenance, Integer>, CustomMaintenanceRepository {

    List<Maintenance> findByVehicleId(Integer id);
}
