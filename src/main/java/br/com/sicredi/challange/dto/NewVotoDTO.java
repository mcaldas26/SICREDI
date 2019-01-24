package br.com.sicredi.challange.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class NewVotoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6690485363386697749L;

	@NotNull(message = "Identificador da pauta é obrigatório")
	@ApiModelProperty(notes = "Identificador da pauta", name = "pauta", required = true, value = "5")
	private Long pauta;
	@NotNull(message = "Identificador do associado é obrigatório")
	@ApiModelProperty(notes = "Identificador do associado", name = "associado", required = true, value = "80aa6s50")
	private String associado;
	@NotNull(message = "A opção de voto é obrigatória")
	@ApiModelProperty(notes = "Voto", name = "voto", required = true, value = "true")
	private Boolean voto;

	public NewVotoDTO() {
	}

	public NewVotoDTO(Long pauta, String associado, Boolean voto) {
		super();
		this.pauta = pauta;
		this.associado = associado;
		this.voto = voto;
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

	public Boolean getVoto() {
		return voto;
	}

	public void setVoto(Boolean voto) {
		this.voto = voto;
	}
}
