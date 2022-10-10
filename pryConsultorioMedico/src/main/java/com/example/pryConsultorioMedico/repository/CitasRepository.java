package com.example.pryConsultorioMedico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pryConsultorioMedico.model.Citas;

@Repository
public interface CitasRepository extends JpaRepository<Citas, Long> {

}
