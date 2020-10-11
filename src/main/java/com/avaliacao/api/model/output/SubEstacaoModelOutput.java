package com.avaliacao.api.model.output;

import java.math.BigDecimal;
import java.util.List;

public class SubEstacaoModelOutput {
	
	private String codigo;
	
	private String nome;
	
	private BigDecimal latitude;
	
	private BigDecimal longitude;
	
	private List<RedeMtModelOutput> redesMt;

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

	public List<RedeMtModelOutput> getRedesMt() {
		return redesMt;
	}

	public void setRedesMt(List<RedeMtModelOutput> redesMt) {
		this.redesMt = redesMt;
	}
	
}
