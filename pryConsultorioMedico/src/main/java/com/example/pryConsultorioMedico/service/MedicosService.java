package com.example.pryConsultorioMedico.service;

import java.util.List;
import java.util.Optional;

import com.example.pryConsultorioMedico.model.Horario;
import com.example.pryConsultorioMedico.model.Medicos;

public interface MedicosService {

	public List<Medicos> consultarMedicos();
	public Optional<Medicos> consultarMedicoPorId(Long id);
	public Medicos guardarMedico(Medicos medico);
	public void eliminarMedico(Long id);
}
