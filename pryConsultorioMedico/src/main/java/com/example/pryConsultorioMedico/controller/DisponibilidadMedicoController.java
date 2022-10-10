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

import com.example.pryConsultorioMedico.model.DisponibilidadMedico;
import com.example.pryConsultorioMedico.service.DisponibilidadMedicoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

@RestController
@RequestMapping("/api/disponibilidad_medico")
public class DisponibilidadMedicoController {
	
	@Autowired
	private DisponibilidadMedicoService disponibilidadMedicoService;
	
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody DisponibilidadMedico disponibilidadMedico){
		return ResponseEntity.status(HttpStatus.CREATED).body(disponibilidadMedicoService.guardarDisponibilidadMedico(disponibilidadMedico));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable Long id){
		Optional<DisponibilidadMedico> disponibilidadMedico = disponibilidadMedicoService.consultarDisponibilidadMedicoPorId(id);
		if(!disponibilidadMedico.isPresent()) {
			return ResponseEntity.notFound().build();
		} 
		return ResponseEntity.ok(disponibilidadMedico);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody DisponibilidadMedico disponibilidadMedicoDetalle, @PathVariable Long id){
		Optional<DisponibilidadMedico> disponibilidadMedico = disponibilidadMedicoService.consultarDisponibilidadMedicoPorId(id);
		if(!disponibilidadMedico.isPresent()) {
			return ResponseEntity.notFound().build();
		} 
		BeanUtils.copyProperties(disponibilidadMedicoDetalle, disponibilidadMedico.get());
		disponibilidadMedico.get().setId(id);
		return ResponseEntity.status(HttpStatus.CREATED).body(disponibilidadMedicoService.guardarDisponibilidadMedico(disponibilidadMedico.get()));
	}
	
	@PatchMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updatePartial(@RequestBody JsonPatch patch, @PathVariable Long id){
		try {
			Optional<DisponibilidadMedico> disponibilidadMedico = disponibilidadMedicoService.consultarDisponibilidadMedicoPorId(id);
			if(!disponibilidadMedico.isPresent()) {
				return ResponseEntity.notFound().build();
			}
			
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode patched = patch.apply(objectMapper.convertValue(disponibilidadMedico.get(), JsonNode.class));
			DisponibilidadMedico disponibilidadMedicoPatched = objectMapper.treeToValue(patched,DisponibilidadMedico.class);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(disponibilidadMedicoService.guardarDisponibilidadMedico(disponibilidadMedicoPatched));
	    } catch (JsonPatchException | JsonProcessingException e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		Optional<DisponibilidadMedico> disponibilidadMedico = disponibilidadMedicoService.consultarDisponibilidadMedicoPorId(id);
		if(!disponibilidadMedico.isPresent()) {
			return ResponseEntity.notFound().build();
		} 
		disponibilidadMedicoService.eliminarDisponibilidadMedico(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping
	public List<DisponibilidadMedico> readAll(){
		return disponibilidadMedicoService.consultarDisponibilidadMedico();
	}	
}
