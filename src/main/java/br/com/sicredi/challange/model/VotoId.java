package br.com.sicredi.challange.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class VotoId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5423698273247800244L;

	@Column(name = "pauta")
	private Long pauta;

	@Column(name = "associado")
	private String associado;

	public VotoId() {
	}

	public VotoId(Long pauta, String associado) {
		super();
		this.pauta = pauta;
		this.associado = associado;
	}

	public Long getPauta() {
		return pauta;
	}

	public void setPauta(Long pauta) {
		this.pauta = pauta;
	}

	public String getAssociado() {
		return associado;
	}

	public void setAssociado(String associado) {
		this.associado = associado;
	}

	@Override
	public int hashCode() {
		return Objects.hash(associado, pauta);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof VotoId)) {
			return false;
		}
		VotoId other = (VotoId) obj;
		return Objects.equals(associado, other.associado) && Objects.equals(pauta, other.pauta);
	}
}
