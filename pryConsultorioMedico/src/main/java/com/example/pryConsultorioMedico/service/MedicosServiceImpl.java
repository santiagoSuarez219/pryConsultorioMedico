package com.example.pryConsultorioMedico.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pryConsultorioMedico.model.Medicos;
import com.example.pryConsultorioMedico.repository.MedicosRepository;

@Service
public class MedicosServiceImpl implements MedicosService {

	@Autowired	
	private MedicosRepository repositorio;
	
	@Override
	@Transactional
	public List<Medicos> consultarMedicos() {
		return repositorio.findAll();
	}

	@Override
	@Transactional
	public Optional<Medicos> consultarMedicoPorId(Long id) {
		return repositorio.findById(id);
	}

	@Override
	@Transactional
	public Medicos guardarMedico(Medicos medico) {
		return repositorio.save(medico);
	}

	@Override
	@Transactional
	public void eliminarMedico(Long id) {
		repositorio.deleteById(id);
	}
}
