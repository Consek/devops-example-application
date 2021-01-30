package com.example.backend.data;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstanceRepository extends CrudRepository<Instance, String> {

  List<Instance> findAll();
}
