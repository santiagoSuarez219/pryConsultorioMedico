package com.example.pryConsultorioMedico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pryConsultorioMedico.model.Medicos;

@Repository
public interface MedicosRepository extends JpaRepository<Medicos, Long> {

}
