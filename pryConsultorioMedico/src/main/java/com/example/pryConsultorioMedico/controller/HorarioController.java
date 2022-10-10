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

import com.example.pryConsultorioMedico.model.Horario;
import com.example.pryConsultorioMedico.service.HorarioService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

@RestController
@RequestMapping("/api/horario")
public class HorarioController {
	
	@Autowired
	private HorarioService horarioService;
	
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Horario horario){
		return ResponseEntity.status(HttpStatus.CREATED).body(horarioService.guardarHorario(horario));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable Long id){
		Optional<Horario> horario = horarioService.consultarHorarioPorId(id);
		if(!horario.isPresent()) {
			return ResponseEntity.notFound().build();
		} 
		return ResponseEntity.ok(horario);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Horario horarioDetalle, @PathVariable Long id){
		Optional<Horario> horario = horarioService.consultarHorarioPorId(id);
		if(!horario.isPresent()) {
			return ResponseEntity.notFound().build();
		} 
		BeanUtils.copyProperties(horarioDetalle, horario.get());
		horario.get().setId(id);
		return ResponseEntity.status(HttpStatus.CREATED).body(horarioService.guardarHorario(horario.get()));
	}
	
	@PatchMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updatePartial(@RequestBody JsonPatch patch, @PathVariable Long id){
		try {
			Optional<Horario> horario = horarioService.consultarHorarioPorId(id);
			if(!horario.isPresent()) {
				return ResponseEntity.notFound().build();
			}
			
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode patched = patch.apply(objectMapper.convertValue(horario.get(), JsonNode.class));
			Horario horarioPatched = objectMapper.treeToValue(patched,Horario.class);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(horarioService.guardarHorario(horarioPatched));
	    } catch (JsonPatchException | JsonProcessingException e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		Optional<Horario> horario = horarioService.consultarHorarioPorId(id);
		if(!horario.isPresent()) {
			return ResponseEntity.notFound().build();
		} 
		horarioService.eliminarHorario(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping
	public List<Horario> readAll(){
		return horarioService.consultarHorario();
	}

}
