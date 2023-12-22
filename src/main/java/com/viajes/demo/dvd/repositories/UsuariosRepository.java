package com.viajes.demo.dvd.repositories;

import com.viajes.demo.dvd.entities.UsuariosEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuariosRepository extends JpaRepository<UsuariosEntity, Long>{

}
