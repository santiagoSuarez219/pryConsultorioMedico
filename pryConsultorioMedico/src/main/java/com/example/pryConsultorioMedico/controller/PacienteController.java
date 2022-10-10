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

import com.example.pryConsultorioMedico.model.Paciente;
import com.example.pryConsultorioMedico.service.PacienteService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

@RestController
@RequestMapping("/api/paciente")
public class PacienteController {
	
	@Autowired
	private PacienteService pacienteService;
	
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Paciente paciente){
		return ResponseEntity.status(HttpStatus.CREATED).body(pacienteService.guardarPaciente(paciente));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable Long id){
		Optional<Paciente> paciente = pacienteService.consultarPacientePorId(id);
		if(!paciente.isPresent()) {
			return ResponseEntity.notFound().build();
		} 
		return ResponseEntity.ok(paciente);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Paciente pacienteDetalle, @PathVariable Long id){
		Optional<Paciente> paciente = pacienteService.consultarPacientePorId(id);
		if(!paciente.isPresent()) {
			return ResponseEntity.notFound().build();
		} 
		BeanUtils.copyProperties(pacienteDetalle, paciente.get());
		paciente.get().setId(id);
		return ResponseEntity.status(HttpStatus.CREATED).body(pacienteService.guardarPaciente(paciente.get()));
	}
	
	@PatchMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updatePartial(@RequestBody JsonPatch patch, @PathVariable Long id){
		try {
			Optional<Paciente> paciente = pacienteService.consultarPacientePorId(id);
			if(!paciente.isPresent()) {
				return ResponseEntity.notFound().build();
			}
			
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode patched = patch.apply(objectMapper.convertValue(paciente.get(), JsonNode.class));
			Paciente pacientePatched = objectMapper.treeToValue(patched,Paciente.class);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(pacienteService.guardarPaciente(pacientePatched));
	    } catch (JsonPatchException | JsonProcessingException e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		Optional<Paciente> paciente = pacienteService.consultarPacientePorId(id);
		if(!paciente.isPresent()) {
			return ResponseEntity.notFound().build();
		} 
		pacienteService.eliminarPaciente(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping
	public List<Paciente> readAll(){
		return pacienteService.consultarPacientes();
	}

}
