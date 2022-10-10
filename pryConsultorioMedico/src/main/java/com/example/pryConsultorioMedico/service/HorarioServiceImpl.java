package com.example.pryConsultorioMedico.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pryConsultorioMedico.model.Horario;
import com.example.pryConsultorioMedico.repository.HorarioRepository;

@Service
public class HorarioServiceImpl implements HorarioService {
	
	@Autowired
	private HorarioRepository repositorio;
	
	@Override
	@Transactional
	public List<Horario> consultarHorario() {
		return repositorio.findAll();
	}

	@Override
	@Transactional
	public Optional<Horario> consultarHorarioPorId(Long id) {
		return repositorio.findById(id);
	}

	@Override
	@Transactional
	public Horario guardarHorario(Horario horario) {
		return repositorio.save(horario);
	}

	@Override
	@Transactional
	public void eliminarHorario(Long id) {
		repositorio.deleteById(id);
	}

}
