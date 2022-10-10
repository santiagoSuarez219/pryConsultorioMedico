package com.example.pryConsultorioMedico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pryConsultorioMedico.model.Fechas;

@Repository
public interface FechasRepository extends JpaRepository<Fechas, Long> {

}
