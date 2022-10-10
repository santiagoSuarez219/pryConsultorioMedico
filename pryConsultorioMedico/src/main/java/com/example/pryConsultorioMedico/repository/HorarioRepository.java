package com.example.pryConsultorioMedico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pryConsultorioMedico.model.Horario;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Long> {

}
