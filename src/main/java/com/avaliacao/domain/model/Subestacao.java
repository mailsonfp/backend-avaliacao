package com.avaliacao.domain.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name="tb_subestacao")
public class SubEstacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_subestacao")
	private Integer idSubestacao;
	
	private String codigo;
	
	private String nome;
	
	private BigDecimal latitude;
	
	private BigDecimal longitude;
	
	@OneToMany(mappedBy = "subEstacao", cascade = CascadeType.ALL)
	private List<RedeMt> redesMt = new ArrayList<>();

	public Integer getIdSubestacao() {
		return idSubestacao;
	}

	public void setIdSubestacao(Integer idSubestacao) {
		this.idSubestacao = idSubestacao;
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

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public List<RedeMt> getRedesMt() {
		return redesMt;
	}

	public void setRedesMt(List<RedeMt> redesMt) {
		this.redesMt = redesMt;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idSubestacao == null) ? 0 : idSubestacao.hashCode());
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
		SubEstacao other = (SubEstacao) obj;
		if (idSubestacao == null) {
			if (other.idSubestacao != null)
				return false;
		} else if (!idSubestacao.equals(other.idSubestacao))
			return false;
		return true;
	}	

}
