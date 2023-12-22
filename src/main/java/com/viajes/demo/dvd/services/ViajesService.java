package com.viajes.demo.dvd.services;

import java.util.Optional;

import com.viajes.demo.dvd.entities.ViajesEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ViajesService {
	
	public Iterable<ViajesEntity> findAll();
	
	public Page<ViajesEntity> findAll(Pageable pageable);
	
	public Optional<ViajesEntity> findById(Long id);
	
	public ViajesEntity save(ViajesEntity viajesEntity);
	
	public void deleteById(Long id);

}
