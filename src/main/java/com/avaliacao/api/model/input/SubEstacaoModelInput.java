package com.avaliacao.api.model.input;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SubEstacaoModelInput {
	
	@Size(min = 1, max = 3)
	private String codigo;
	
	@NotBlank
	private String nome;
	
	@NotNull
	private BigDecimal latitude;
	
	@NotNull
	private BigDecimal longitude;
	
	@Valid
	private List<RedeMtModelInput> redesMt;
	
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

	public List<RedeMtModelInput> getRedesMt() {
		return redesMt;
	}

	public void setRedesMt(List<RedeMtModelInput> redesMt) {
		this.redesMt = redesMt;
	}
}
