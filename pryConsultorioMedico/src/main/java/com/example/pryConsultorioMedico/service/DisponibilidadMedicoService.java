package com.example.pryConsultorioMedico.service;

import java.util.List;
import java.util.Optional;

import com.example.pryConsultorioMedico.model.DisponibilidadMedico;

public interface DisponibilidadMedicoService {
	public List<DisponibilidadMedico> consultarDisponibilidadMedico();
	public Optional<DisponibilidadMedico> consultarDisponibilidadMedicoPorId(Long id);
	public DisponibilidadMedico guardarDisponibilidadMedico(DisponibilidadMedico disponibilidadMedico);
	public void eliminarDisponibilidadMedico(Long id);
}
