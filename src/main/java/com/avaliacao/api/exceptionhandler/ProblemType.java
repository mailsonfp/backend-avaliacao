package com.avaliacao.api.exceptionhandler;

public enum ProblemType {
	
	RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado"),
	ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
	ERRO_NEGOCIO("/bad-request", "Violação de regra de negócio"),
	JSON_INVALIDO("/json-invalido","Json inválido"),
	PARAMETRO_INVALIDO("/parametro-inválido","Parâmetro inválido"),
	ERRO_DE_SISTEMA("/erro-de-sistema", "Erro interno"),
	ACESSO_NEGADO("/acesso-negado", "Acesso negado");
	
	private String title;
	private String uri;
	
	private ProblemType(String path, String title) {
		this.uri = "https://avaliacaosinapsis.com.br" + path;
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public String getUri() {
		return uri;
	}
	
}
