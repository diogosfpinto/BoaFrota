package com.frotas.FirstProject.repository;

import com.frotas.FirstProject.model.User;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.criteria.CriteriaBuilder;

public interface UserRepository extends CrudRepository<User, Integer> {

}
