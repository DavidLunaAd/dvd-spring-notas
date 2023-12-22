package com.viajes.demo.dvd.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.viajes.demo.dvd.entities.ViajesEntity;
import com.viajes.demo.dvd.services.ViajesService;

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
@RequestMapping("/api/viajes")
public class ViajesController {
	
	@Autowired
	private ViajesService viajesService;
	
	//Create a new viaje
	
	@PostMapping
	public ResponseEntity<?> create (@RequestBody ViajesEntity viajeEntity){
		return ResponseEntity.status(HttpStatus.CREATED).body(viajesService.save(viajeEntity));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable(value = "id") Long viajesId){
		Optional<ViajesEntity> oViajes = viajesService.findById(viajesId);
			if(!oViajes.isPresent()) {
				return ResponseEntity.notFound().build();		
				}
		return ResponseEntity.ok(oViajes);
		
	}

	//Update an Viaje
		@PutMapping("/{id}")
		public ResponseEntity<?> update(@RequestBody ViajesEntity viajeDetails, @PathVariable(value = "id") Long viajesId){
			Optional<ViajesEntity> viaje = viajesService.findById(viajesId);
			
			if(!viaje.isPresent()) {
				return ResponseEntity.notFound().build();
			}
						
			viaje.get().setCoche(viajeDetails.getCoche());
			viaje.get().setHorario(viajeDetails.getHorario());
			viaje.get().setDueno(viajeDetails.getDueno());
			
			return ResponseEntity.status(HttpStatus.CREATED).body(viajesService.save(viaje.get()));
		}

	//Delete an Viaje
		@DeleteMapping("{id}")
		public ResponseEntity<?> delete (@PathVariable (value = "id") Long viajesId){
			if(!viajesService.findById(viajesId).isPresent()) {
				return ResponseEntity.notFound().build();
			}
			
			viajesService.deleteById(viajesId);
			return ResponseEntity.ok().build();
		}
	
	//Read All viajesEntity
	@GetMapping
	public List<ViajesEntity> getAll(){
		
		//Convierte iterable en Lista con Stream
				List<ViajesEntity> viajes = StreamSupport
						.stream(viajesService.findAll().spliterator(), false) //recorre un iterable, false = secuencial
						.collect(Collectors.toList());		// agrega a lista
				
				return viajes;
	}

}
