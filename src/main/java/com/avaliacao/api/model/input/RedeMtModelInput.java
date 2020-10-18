package com.avaliacao.api.model.input;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RedeMtModelInput {
	
	@Size(min = 1, max = 5)
	private String codigo;
	
	@NotBlank
	private String nome;
	
	@NotNull
	private BigDecimal tensao_nominal;

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

}
