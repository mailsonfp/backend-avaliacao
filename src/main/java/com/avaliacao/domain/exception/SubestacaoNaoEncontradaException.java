package com.avaliacao.domain.exception;

public class SubEstacaoNaoEncontradaException extends EntidadeNaoEncontradaException {
	
	private static final long serialVersionUID = 1L;
	
	 public SubEstacaoNaoEncontradaException(String codigoSubEstacao) {
	        super(String.format("Não foi possível localizar uma SubEstação com o código: %s", codigoSubEstacao));
	 }


}
