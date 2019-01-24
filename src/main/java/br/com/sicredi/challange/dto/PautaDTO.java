package br.com.sicredi.challange.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModelProperty;

public class PautaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6492805947136928695L;

	@ApiModelProperty(notes = "Identificador da pauta", name = "id", value = "5")
	private Long id;
	@ApiModelProperty(notes = "Título da pauta", name = "titulo", value = "Pauta 'A'")
	private String titulo;
	@ApiModelProperty(notes = "Descrição da pauta", name = "descricao", value = "Pauta para apreciação da excução ...")
	private String descricao;
	@ApiModelProperty(notes = "Data/Hora de início da votação da pauta", name = "dtInicioVotacao")
	private LocalDateTime dtInicioVotacao;
	@ApiModelProperty(notes = "Data/Hora de fim da votação da pauta", name = "dtFimVotacao")
	private LocalDateTime dtFimVotacao;

	public PautaDTO() {
	}

	public PautaDTO(Long id, String titulo, String descricao, LocalDateTime dtInicioVotacao,
			LocalDateTime dtFimVotacao) {
		super();
		this.id = id;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((dtFimVotacao == null) ? 0 : dtFimVotacao.hashCode());
		result = prime * result + ((dtInicioVotacao == null) ? 0 : dtInicioVotacao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof PautaDTO)) {
			return false;
		}
		PautaDTO other = (PautaDTO) obj;
		if (descricao == null) {
			if (other.descricao != null) {
				return false;
			}
		} else if (!descricao.equals(other.descricao)) {
			return false;
		}
		if (dtFimVotacao == null) {
			if (other.dtFimVotacao != null) {
				return false;
			}
		} else if (!dtFimVotacao.equals(other.dtFimVotacao)) {
			return false;
		}
		if (dtInicioVotacao == null) {
			if (other.dtInicioVotacao != null) {
				return false;
			}
		} else if (!dtInicioVotacao.equals(other.dtInicioVotacao)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (titulo == null) {
			if (other.titulo != null) {
				return false;
			}
		} else if (!titulo.equals(other.titulo)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "PautaDTO [id=" + id + ", titulo=" + titulo + ", descricao=" + descricao + ", dtInicioVotacao="
				+ dtInicioVotacao + ", dtFimVotacao=" + dtFimVotacao + "]";
	}
}
