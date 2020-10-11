package com.avaliacao.api.model.output;

import java.math.BigDecimal;

public class RedeMtModelOutput {
	
	private String codigo;

	private String nome;
	
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
