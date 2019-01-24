package br.com.sicredi.challange.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class ResultDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1788444958756300743L;

	@ApiModelProperty(notes = "Identificador da pauta", name = "id", value = "5")
	private Long id;
	@ApiModelProperty(notes = "Título da pauta", name = "titulo", value = "Pauta 'A'")
	private String titulo;
	@ApiModelProperty(notes = "Total de votos 'Sim' na pauta", name = "totalVotosSim", value = "50")
	public Long totalVotosSim;
	@ApiModelProperty(notes = "Total de votos 'Não' na pauta", name = "totalVotosNao", value = "5")
	public Long totalVotosNao;
	@ApiModelProperty(notes = "Resultado da votação na pauta", name = "resultado", value = "Aprovada")
	public String resultado;

	public ResultDTO() {
	}

	public ResultDTO(Long id, String titulo, Long totalVotosSim, Long totalVotosNao, String resultado) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.totalVotosSim = totalVotosSim;
		this.totalVotosNao = totalVotosNao;
		this.resultado = resultado;
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

	public Long getTotalVotosSim() {
		return totalVotosSim;
	}

	public void setTotalVotosSim(Long totalVotosSim) {
		this.totalVotosSim = totalVotosSim;
	}

	public Long getTotalVotosNao() {
		return totalVotosNao;
	}

	public void setTotalVotosNao(Long totalVotosNao) {
		this.totalVotosNao = totalVotosNao;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
}
