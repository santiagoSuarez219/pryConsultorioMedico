package com.example.pryConsultorioMedico.service;

import java.util.List;
import java.util.Optional;

import com.example.pryConsultorioMedico.model.Usuarios;


public interface UsuariosService {
	public List<Usuarios> consultarUsuarios();
	public Optional<Usuarios> consultarUsuarioPorId(Long id);
	public Usuarios guardarUsuario(Usuarios usuario);
	public void eliminarUsuario(Long id);
}
