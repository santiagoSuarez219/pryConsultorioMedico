package com.example.pryConsultorioMedico.service;

import java.util.List;
import java.util.Optional;

import com.example.pryConsultorioMedico.model.Paciente;

public interface PacienteService {
	public List<Paciente> consultarPacientes();
	public Optional<Paciente> consultarPacientePorId(Long id);
	public Paciente guardarPaciente(Paciente paciente);
	public void eliminarPaciente(Long id);
}
