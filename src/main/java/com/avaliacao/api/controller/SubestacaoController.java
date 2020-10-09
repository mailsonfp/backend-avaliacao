package com.avaliacao.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.avaliacao.domain.model.Subestacao;
import com.avaliacao.domain.service.SubestacaoService;

@RestController
@RequestMapping(path = "/subestacoes" )
public class SubestacaoController {
	
	@Autowired
	private SubestacaoService subestacaoService;
	
	@GetMapping
	public List<Subestacao> listar(){
		return subestacaoService.listar();
	}
	
	@GetMapping(value = "{idSubestacao}")
	public Subestacao buscar(@PathVariable Integer idSubestacao) {
		return subestacaoService.buscar(idSubestacao);
	}

}
