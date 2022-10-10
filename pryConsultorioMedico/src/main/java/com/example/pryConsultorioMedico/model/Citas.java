package com.example.pryConsultorioMedico.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "citas")
public class Citas implements Serializable {

	private static final long serialVersionUID = 3628715442380403613L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
    @JoinColumn(name = "medico") 
	private Medicos medico;
	
	@ManyToOne
    @JoinColumn(name = "paciente") 
	private Paciente paciente;
	
	@ManyToOne
    @JoinColumn(name = "horario") 
	private Horario horario;
	
	@ManyToOne
    @JoinColumn(name = "fecha") 
	private Fechas fecha;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Medicos getMedico() {
		return medico;
	}

	public void setMedico(Medicos medico) {
		this.medico = medico;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Horario getHorario() {
		return horario;
	}

	public void setHorario(Horario horario) {
		this.horario = horario;
	}

	public Fechas getFecha() {
		return fecha;
	}

	public void setFecha(Fechas fecha) {
		this.fecha = fecha;
	}
}
