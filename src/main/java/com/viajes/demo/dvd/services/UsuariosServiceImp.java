package com.viajes.demo.dvd.services;

import java.util.Optional;

import com.viajes.demo.dvd.entities.UsuariosEntity;
import com.viajes.demo.dvd.repositories.UsuariosRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuariosServiceImp implements UsuariosService {

	@Autowired
	private UsuariosRepository usuariosRepository;
	
	@Override
	@Transactional(readOnly = true)
	public Iterable<UsuariosEntity> findAll() {
		return usuariosRepository.findAll();

	}

	@Override
	@Transactional(readOnly = true)
	public Page<UsuariosEntity> findAll(Pageable pageable) {
		return usuariosRepository.findAll(pageable);

	}

	@Override
	@Transactional(readOnly = true)
	public Optional<UsuariosEntity> findById(Long id) {
		return usuariosRepository.findById(id);

	}

	@Override
	@Transactional
	public UsuariosEntity save(UsuariosEntity usuariosEntity) {
		return usuariosRepository.save(usuariosEntity);

	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		usuariosRepository.deleteById(id);

	}

}
