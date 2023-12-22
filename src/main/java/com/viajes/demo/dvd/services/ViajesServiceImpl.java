package com.viajes.demo.dvd.services;

import java.util.Optional;

import com.viajes.demo.dvd.entities.ViajesEntity;
import com.viajes.demo.dvd.repositories.ViajesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ViajesServiceImpl implements ViajesService{
	
	@Autowired
	private ViajesRepository viajesRepository;

	@Override
	@Transactional(readOnly = true)
	public Iterable<ViajesEntity> findAll() {
		return viajesRepository.findAll();

	}

	@Override
	@Transactional(readOnly = true)
	public Page<ViajesEntity> findAll(Pageable pageable) {
		return viajesRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<ViajesEntity> findById(Long id) {
		return viajesRepository.findById(id);
	}

	@Override
	@Transactional
	public ViajesEntity save(ViajesEntity viajesEntity) {
		return viajesRepository.save(viajesEntity);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		viajesRepository.deleteById(id);
		
	}

	
}
