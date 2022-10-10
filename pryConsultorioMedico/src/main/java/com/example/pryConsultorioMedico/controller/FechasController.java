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

import com.example.pryConsultorioMedico.model.Fechas;
import com.example.pryConsultorioMedico.service.FechasService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

@RestController
@RequestMapping("/api/fechas")
public class FechasController {

	@Autowired
	private FechasService fechasService;
	
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Fechas fechas){
		return ResponseEntity.status(HttpStatus.CREATED).body(fechasService.guardarFecha(fechas));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable Long id){
		Optional<Fechas> fechas = fechasService.consultarFechaPorId(id);
		if(!fechas.isPresent()) {
			return ResponseEntity.notFound().build();
		} 
		return ResponseEntity.ok(fechas);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Fechas fechasDetalle, @PathVariable Long id){
		Optional<Fechas> fechas = fechasService.consultarFechaPorId(id);
		if(!fechas.isPresent()) {
			return ResponseEntity.notFound().build();
		} 
		BeanUtils.copyProperties(fechasDetalle, fechas.get());
		fechas.get().setId(id);
		return ResponseEntity.status(HttpStatus.CREATED).body(fechasService.guardarFecha(fechas.get()));
	}
	
	@PatchMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updatePartial(@RequestBody JsonPatch patch, @PathVariable Long id){
		try {
			Optional<Fechas> fechas = fechasService.consultarFechaPorId(id);
			if(!fechas.isPresent()) {
				return ResponseEntity.notFound().build();
			}
			
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode patched = patch.apply(objectMapper.convertValue(fechas.get(), JsonNode.class));
			Fechas fechasPatched = objectMapper.treeToValue(patched,Fechas.class);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(fechasService.guardarFecha(fechasPatched));
	    } catch (JsonPatchException | JsonProcessingException e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		Optional<Fechas> fechas = fechasService.consultarFechaPorId(id);
		if(!fechas.isPresent()) {
			return ResponseEntity.notFound().build();
		} 
		fechasService.eliminarFecha(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping
	public List<Fechas> readAll(){
		return fechasService.consultarFechas();
	}

}
