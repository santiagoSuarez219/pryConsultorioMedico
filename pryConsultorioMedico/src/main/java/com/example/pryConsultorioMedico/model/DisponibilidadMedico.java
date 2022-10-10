package com.example.pryConsultorioMedico.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "disponibilidad_medico")
public class DisponibilidadMedico implements Serializable {

	private static final long serialVersionUID = -2578198640026326192L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="hora_inicio", length = 50)
	private String horaInicio;
	
	@ManyToOne
    @JoinColumn(name = "medico") 
	private Medicos medico;
	
	@ManyToOne
    @JoinColumn(name = "fecha") 
	private Fechas fecha;

	public Long getId() {
		return id;
	}

	public Fechas getFecha() {
		return fecha;
	}

	public void setFecha(Fechas fecha) {
		this.fecha = fecha;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Medicos getMedico() {
		return medico;
	}

	public void setMedico(Medicos medico) {
		this.medico = medico;
	}
}
