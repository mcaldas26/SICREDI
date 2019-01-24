package br.com.sicredi.challange.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "pauta")
public class Pauta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5058890946097215493L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pauta_generator")
	@SequenceGenerator(name = "pauta_generator", sequenceName = "pauta_seq", allocationSize = 1)
	@Column(name = "id")
	private Long id;

	@Column(name = "titulo", nullable = false)
	private String titulo;

	@Column(name = "descricao")
	private String descricao;

	@Column(name = "dtInicioVotacao")
	private LocalDateTime dtInicioVotacao;

	@Column(name = "dtFimVotacao")
	private LocalDateTime dtFimVotacao;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "pauta")
	@JsonIgnore
	private List<Voto> votos = new ArrayList<>();

	public Pauta() {
	}

	public Pauta(Long id, String titulo, String descricao, LocalDateTime dtInicioVotacao, LocalDateTime dtFimVotacao) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.dtInicioVotacao = dtInicioVotacao;
		this.dtFimVotacao = dtFimVotacao;
	}

	public Pauta(String titulo, String descricao, LocalDateTime dtInicioVotacao, LocalDateTime dtFimVotacao) {
		this.titulo = titulo;
		this.descricao = descricao;
		this.dtInicioVotacao = dtInicioVotacao;
		this.dtFimVotacao = dtFimVotacao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDateTime getDtInicioVotacao() {
		return dtInicioVotacao;
	}

	public void setDtInicioVotacao(LocalDateTime dtInicioVotacao) {
		this.dtInicioVotacao = dtInicioVotacao;
	}

	public LocalDateTime getDtFimVotacao() {
		return dtFimVotacao;
	}

	public void setDtFimVotacao(LocalDateTime dtFimVotacao) {
		this.dtFimVotacao = dtFimVotacao;
	}

	public List<Voto> getVotos() {
		return votos;
	}

	public void setVotos(List<Voto> votos) {
		this.votos = votos;
	}
}
