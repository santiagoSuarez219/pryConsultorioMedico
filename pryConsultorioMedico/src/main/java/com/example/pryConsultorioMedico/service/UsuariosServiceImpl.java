package com.example.pryConsultorioMedico.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pryConsultorioMedico.model.Usuarios;
import com.example.pryConsultorioMedico.repository.UsuariosRepository;

@Service
public class UsuariosServiceImpl implements UsuariosService{

	@Autowired
	private UsuariosRepository repositorio;
	
	@Override
	@Transactional
	public List<Usuarios> consultarUsuarios() {
		return repositorio.findAll();
	}

	@Override
	@Transactional
	public Optional<Usuarios> consultarUsuarioPorId(Long id) {
		return repositorio.findById(id);
	}

	@Override
	@Transactional
	public Usuarios guardarUsuario(Usuarios usuario) {
		return repositorio.save(usuario);
	}

	@Override
	@Transactional
	public void eliminarUsuario(Long id) {
		repositorio.deleteById(id);
		
	}

}
