package com.ps.ElectronicsStore.repository;

import com.ps.ElectronicsStore.model.ConnectionTime;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConnectionTimeRepository extends CrudRepository<ConnectionTime, Long> {
    List<ConnectionTime> findAll();
    //ConnectionTime findByFinished(Boolean bool);
}
