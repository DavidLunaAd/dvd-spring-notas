package com.viajes.demo.dvd.services;

import java.util.Optional;

import com.viajes.demo.dvd.entities.UsuariosEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UsuariosService {
	
	public Iterable<UsuariosEntity> findAll();
	
	public Page<UsuariosEntity> findAll(Pageable pageable);
	
	public Optional<UsuariosEntity> findById(Long id);
	
	public UsuariosEntity save(UsuariosEntity usuariosEntity);
	
	public void deleteById(Long id);
	

}
