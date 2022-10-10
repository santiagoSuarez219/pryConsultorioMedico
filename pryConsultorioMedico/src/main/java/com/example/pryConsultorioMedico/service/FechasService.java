package com.example.pryConsultorioMedico.service;

import java.util.List;
import java.util.Optional;

import com.example.pryConsultorioMedico.model.Fechas;

public interface FechasService {
	public List<Fechas> consultarFechas();
	public Optional<Fechas> consultarFechaPorId(Long id);
	public Fechas guardarFecha(Fechas fecha);
	public void eliminarFecha(Long id);
}
