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

import com.example.pryConsultorioMedico.model.Usuarios;
import com.example.pryConsultorioMedico.service.UsuariosService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

@RestController
@RequestMapping("/api/usuarios")
public class UsuariosController {
	
	@Autowired
	private UsuariosService usuariosService;
	
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Usuarios usuario){
		return ResponseEntity.status(HttpStatus.CREATED).body(usuariosService.guardarUsuario(usuario));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable Long id){
		Optional<Usuarios> usuario = usuariosService.consultarUsuarioPorId(id);
		if(!usuario.isPresent()) {
			return ResponseEntity.notFound().build();
		} 
		return ResponseEntity.ok(usuario);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Usuarios usuarioDetalle, @PathVariable Long id){
		Optional<Usuarios> usuario = usuariosService.consultarUsuarioPorId(id);
		if(!usuario.isPresent()) {
			return ResponseEntity.notFound().build();
		} 
		BeanUtils.copyProperties(usuarioDetalle, usuario.get());
		usuario.get().setId(id);
		return ResponseEntity.status(HttpStatus.CREATED).body(usuariosService.guardarUsuario(usuario.get()));
	}
	
	@PatchMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updatePartial(@RequestBody JsonPatch patch, @PathVariable Long id){
		try {
			Optional<Usuarios> usuario = usuariosService.consultarUsuarioPorId(id);
			if(!usuario.isPresent()) {
				return ResponseEntity.notFound().build();
			}
			
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode patched = patch.apply(objectMapper.convertValue(usuario.get(), JsonNode.class));
		    Usuarios usuarioPatched = objectMapper.treeToValue(patched,Usuarios.class);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(usuariosService.guardarUsuario(usuarioPatched));
	    } catch (JsonPatchException | JsonProcessingException e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		Optional<Usuarios> usuario = usuariosService.consultarUsuarioPorId(id);
		if(!usuario.isPresent()) {
			return ResponseEntity.notFound().build();
		} 
		usuariosService.eliminarUsuario(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping
	public List<Usuarios> readAll(){
		return usuariosService.consultarUsuarios();
	}
}
