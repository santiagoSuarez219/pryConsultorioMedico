package com.example.pryConsultorioMedico.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pryConsultorioMedico.model.DisponibilidadMedico;
import com.example.pryConsultorioMedico.repository.DisponibilidadMedicoRepository;

@Service
public class DisponibilidadMedicoServiceImpl implements DisponibilidadMedicoService {

	@Autowired
	private DisponibilidadMedicoRepository repositorio;
	
	@Override
	@Transactional
	public List<DisponibilidadMedico> consultarDisponibilidadMedico() {
		return repositorio.findAll();
	}

	@Override
	@Transactional
	public Optional<DisponibilidadMedico> consultarDisponibilidadMedicoPorId(Long id) {
		return repositorio.findById(id);
	}

	@Override
	@Transactional
	public DisponibilidadMedico guardarDisponibilidadMedico(DisponibilidadMedico disponibilidadMedico) {
		return repositorio.save(disponibilidadMedico);
	}

	@Override
	@Transactional
	public void eliminarDisponibilidadMedico(Long id) {
		repositorio.deleteById(id);
	}

}
