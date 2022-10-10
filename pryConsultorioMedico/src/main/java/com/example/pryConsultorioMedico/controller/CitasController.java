package com.example.pryConsultorioMedico.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pryConsultorioMedico.model.Citas;
import com.example.pryConsultorioMedico.service.CitasService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

@RestController
@RequestMapping("/api/citas")
public class CitasController {

	@Autowired
	private CitasService citasService;
	
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Citas cita){
		return ResponseEntity.status(HttpStatus.CREATED).body(citasService.guardarCita(cita));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable Long id){
		Optional<Citas> cita = citasService.consultarCitaPorId(id);
		if(!cita.isPresent()) {
			return ResponseEntity.notFound().build();
		} 
		return ResponseEntity.ok(cita);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Citas citaDetalle, @PathVariable Long id){
		Optional<Citas> cita = citasService.consultarCitaPorId(id);
		if(!cita.isPresent()) {
			return ResponseEntity.notFound().build();
		} 
		BeanUtils.copyProperties(citaDetalle, cita.get());
		cita.get().setId(id);
		return ResponseEntity.status(HttpStatus.CREATED).body(citasService.guardarCita(cita.get()));
	}
	
	@PatchMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updatePartial(@RequestBody JsonPatch patch, @PathVariable Long id){
		try {
			Optional<Citas> cita = citasService.consultarCitaPorId(id);
			if(!cita.isPresent()) {
				return ResponseEntity.notFound().build();
			}
			
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode patched = patch.apply(objectMapper.convertValue(cita.get(), JsonNode.class));
		    Citas citaPatched = objectMapper.treeToValue(patched,Citas.class);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(citasService.guardarCita(citaPatched));
	    } catch (JsonPatchException | JsonProcessingException e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		Optional<Citas> cita = citasService.consultarCitaPorId(id);
		if(!cita.isPresent()) {
			return ResponseEntity.notFound().build();
		} 
		citasService.eliminarCita(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping
	public List<Citas> readAll(){
		return citasService.consultarCitas();
	}
}
