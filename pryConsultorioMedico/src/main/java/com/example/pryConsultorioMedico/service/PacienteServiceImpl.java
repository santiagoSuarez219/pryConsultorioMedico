package com.example.pryConsultorioMedico.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pryConsultorioMedico.model.Paciente;
import com.example.pryConsultorioMedico.repository.PacienteRepository;

@Service
public class PacienteServiceImpl implements PacienteService {

	@Autowired
	private PacienteRepository repositorio;
	
	@Override
	@Transactional
	public List<Paciente> consultarPacientes() {
		return repositorio.findAll();
	}

	@Override
	@Transactional
	public Optional<Paciente> consultarPacientePorId(Long id) {
		return repositorio.findById(id);
	}

	@Override
	@Transactional
	public Paciente guardarPaciente(Paciente paciente) {
		return repositorio.save(paciente);
	}

	@Override
	@Transactional
	public void eliminarPaciente(Long id) {
		repositorio.deleteById(id);
	}

}
