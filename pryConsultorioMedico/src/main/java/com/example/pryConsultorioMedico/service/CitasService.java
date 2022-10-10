package com.example.pryConsultorioMedico.service;

import java.util.List;
import java.util.Optional;

import com.example.pryConsultorioMedico.model.Citas;

public interface CitasService {
	public List<Citas> consultarCitas();
	public Optional<Citas> consultarCitaPorId(Long id);
	public Citas guardarCita(Citas citas);
	public void eliminarCita(Long id);
}
