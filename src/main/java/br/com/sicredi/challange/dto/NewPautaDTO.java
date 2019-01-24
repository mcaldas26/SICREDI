package br.com.sicredi.challange.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

public class NewPautaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4850999933110712330L;

	@NotBlank(message = "Título não pode ser vazio")
	@ApiModelProperty(notes = "Título da pauta", name = "titulo", required = true, value = "Pauta 'A'")
	private String titulo;
	@ApiModelProperty(notes = "Descrição da pauta", name = "descricao", required = false, value = "Pauta para apreciação da excução ...")
	private String descricao;

	public NewPautaDTO() {
	}

	public NewPautaDTO(String titulo, String descricao) {
		super();
		this.titulo = titulo;
		this.descricao = descricao;
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
}
