package com.viajes.demo.dvd.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.viajes.demo.dvd.entities.UsuariosEntity;
import com.viajes.demo.dvd.services.UsuariosService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuarios")
public class UsuariosController {
	
	@Autowired
	private UsuariosService usuariosService;
	
	//Create a new viaje
	
	@PostMapping
	public ResponseEntity<?> create (@RequestBody UsuariosEntity usuarioEntity){
		return ResponseEntity.status(HttpStatus.CREATED).body(usuariosService.save(usuarioEntity));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable(value = "id") Long usuarioId){
		Optional<UsuariosEntity> oUsuario = usuariosService.findById(usuarioId);
			if(!oUsuario.isPresent()) {
				return ResponseEntity.notFound().build();		
				}
		return ResponseEntity.ok(oUsuario);
		
	}

	//Update an Viaje
		@PutMapping("/{id}")
		public ResponseEntity<?> update(@RequestBody UsuariosEntity usuarioDetails, @PathVariable(value = "id") Long usuarioId){
			Optional<UsuariosEntity> usuario =  usuariosService.findById(usuarioId);
			
			if(!usuario.isPresent()) {
				return ResponseEntity.notFound().build();
			}
						
			usuario.get().setNombre(usuarioDetails.getNombre());
			usuario.get().setTelefono(usuarioDetails.getTelefono());
			
			return ResponseEntity.status(HttpStatus.CREATED).body(usuariosService.save(usuario.get()));
		}

	//Delete an Viaje
		@DeleteMapping("{id}")
		public ResponseEntity<?> delete (@PathVariable (value = "id") Long usuarioId){
			if(!usuariosService.findById(usuarioId).isPresent()) {
				return ResponseEntity.notFound().build();
			}
						usuariosService.deleteById(usuarioId);
			return ResponseEntity.ok().build();
		}
	
	//Read All viajesEntity
	@GetMapping
	public List<UsuariosEntity> getAll(){
		
		//Convierte iterable en Lista con Stream
				List<UsuariosEntity> usuarios = StreamSupport
						.stream(usuariosService.findAll().spliterator(), false) //recorre un iterable, false = secuencial
						.collect(Collectors.toList());		// agrega a lista
				
				return usuarios;
	}

}
