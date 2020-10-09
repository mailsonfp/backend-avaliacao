package com.avaliacao.domain.exception;

public class SubestacaoNaoEncontradaException extends EntidadeNaoEncontradaException {
	
	private static final long serialVersionUID = 1L;
	
	public SubestacaoNaoEncontradaException(String mensagem) {
		super(mensagem);		
	}
	
	public SubestacaoNaoEncontradaException(Integer idSubestacao) {
		this(String.format("Não foi possível localizar uma subestacao com o código: %d", idSubestacao));
	}	

}
