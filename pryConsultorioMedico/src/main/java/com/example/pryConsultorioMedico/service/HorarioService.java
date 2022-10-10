package com.example.pryConsultorioMedico.service;

import java.util.List;
import java.util.Optional;

import com.example.pryConsultorioMedico.model.Horario;

public interface HorarioService {
	public List<Horario> consultarHorario();
	public Optional<Horario> consultarHorarioPorId(Long id);
	public Horario guardarHorario(Horario horario);
	public void eliminarHorario(Long id);
}
