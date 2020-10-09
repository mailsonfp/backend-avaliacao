package com.avaliacao.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avaliacao.domain.exception.SubestacaoNaoEncontradaException;
import com.avaliacao.domain.model.Subestacao;
import com.avaliacao.domain.repository.SubestacaoRepository;

@Service
public class SubestacaoService {
	
	@Autowired
	private SubestacaoRepository subestacaoRepository;
	
	public List<Subestacao> listar(){
		return subestacaoRepository.findAll();
	}
	
	public Subestacao buscar(Integer idSubestacao) {
		return subestacaoRepository.findById(idSubestacao).orElseThrow(() -> new SubestacaoNaoEncontradaException(idSubestacao));
	}
}
