package com.example.pryConsultorioMedico.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pryConsultorioMedico.model.Fechas;
import com.example.pryConsultorioMedico.repository.FechasRepository;

@Service
public class FechasServiceImpl implements FechasService {

	@Autowired
	private FechasRepository repositorio;
	
	@Override
	@Transactional
	public List<Fechas> consultarFechas() {
		return repositorio.findAll();
	}

	@Override
	@Transactional
	public Optional<Fechas> consultarFechaPorId(Long id) {
		return repositorio.findById(id);
	}

	@Override
	@Transactional
	public Fechas guardarFecha(Fechas fecha) {
		return repositorio.save(fecha);
	}

	@Override
	@Transactional
	public void eliminarFecha(Long id) {
		repositorio.deleteById(id);
	}

}
