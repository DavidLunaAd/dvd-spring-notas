package com.viajes.demo.dvd.repositories;

import com.viajes.demo.dvd.entities.ViajesEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViajesRepository extends JpaRepository<ViajesEntity, Long> {

}
