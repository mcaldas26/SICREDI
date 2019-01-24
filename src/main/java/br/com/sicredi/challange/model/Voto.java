package br.com.sicredi.challange.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "voto")
public class Voto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6765111921125336118L;

	@EmbeddedId
	private VotoId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pauta", insertable = false, updatable = false)
	@JsonIgnore
	private Pauta pauta;

	@Column(name = "voto", nullable = false)
	private Boolean voto;

	public Voto() {
	}

	public Voto(VotoId id, Boolean voto) {
		super();
		this.id = id;
		this.voto = voto;
	}

	public Voto(VotoId id, Pauta pauta, Boolean voto) {
		super();
		this.id = id;
		this.pauta = pauta;
		this.voto = voto;
	}

	public VotoId getId() {
		return id;
	}

	public void setId(VotoId id) {
		this.id = id;
	}

	public Pauta getPauta() {
		return pauta;
	}

	public void setPauta(Pauta pauta) {
		this.pauta = pauta;
	}

	public Boolean getVoto() {
		return voto;
	}

	public void setVoto(Boolean voto) {
		this.voto = voto;
	}
}
