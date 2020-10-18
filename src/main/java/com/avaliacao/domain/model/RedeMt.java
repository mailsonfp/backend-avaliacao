package com.avaliacao.domain.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="tb_rede_mt")
public class RedeMt {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_rede_mt")
	private Integer idRedeMt;
	
	@ManyToOne
	@JoinColumn(name = "id_subestacao", nullable = false)
	private SubEstacao subEstacao;
	
	private String codigo;
	
	private String nome;
	
	private BigDecimal tensao_nominal;

	public Integer getIdRedeMt() {
		return idRedeMt;
	}

	public void setIdRedeMt(Integer idRedeMt) {
		this.idRedeMt = idRedeMt;
	}

	public SubEstacao getSubEstacao() {
		return subEstacao;
	}

	public void setSubEstacao(SubEstacao subEstacao) {
		this.subEstacao = subEstacao;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getTensao_nominal() {
		return tensao_nominal;
	}

	public void setTensao_nominal(BigDecimal tensao_nominal) {
		this.tensao_nominal = tensao_nominal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idRedeMt == null) ? 0 : idRedeMt.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RedeMt other = (RedeMt) obj;
		if (idRedeMt == null) {
			if (other.idRedeMt != null)
				return false;
		} else if (!idRedeMt.equals(other.idRedeMt))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RedeMt [idRedeMt=" + idRedeMt + ", subEstacao=" + subEstacao + ", codigo=" + codigo + ", nome=" + nome
				+ ", tensao_nominal=" + tensao_nominal + "]";
	}
	
	
}
