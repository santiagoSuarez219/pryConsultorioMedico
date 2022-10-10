package com.example.pryConsultorioMedico.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pryConsultorioMedico.model.Citas;
import com.example.pryConsultorioMedico.repository.CitasRepository;

@Service
public class CitasServiceImpl implements CitasService {
	
	@Autowired
	CitasRepository repositorio;
	
	@Override
	@Transactional
	public List<Citas> consultarCitas() {
		return repositorio.findAll();
	}

	@Override
	@Transactional
	public Optional<Citas> consultarCitaPorId(Long id) {
		return repositorio.findById(id);
	}

	@Override
	@Transactional
	public Citas guardarCita(Citas cita) {
		return repositorio.save(cita);
	}

	@Override
	@Transactional
	public void eliminarCita(Long id) {
		repositorio.deleteById(id);

	}

}
