package com.avaliacao.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.avaliacao.api.model.converter.SubEstacaoModelConverter;
import com.avaliacao.api.model.input.SubEstacaoModelInput;
import com.avaliacao.api.model.output.SubEstacaoModelOutput;
import com.avaliacao.domain.model.SubEstacao;
import com.avaliacao.domain.service.SubEstacaoService;

@RestController
@RequestMapping(path = "/subestacoes" )
public class SubestacaoController {
	
	@Autowired
	private SubEstacaoService subEstacaoService;
	
	@Autowired
	private SubEstacaoModelConverter subEstacaoConverter;
	
	@GetMapping
	public List<SubEstacaoModelOutput> listar(){
		return subEstacaoConverter.toCollectionModel(subEstacaoService.listar());
	}
	
	@GetMapping(value = "/{codigoSubEstacao}")
	public SubEstacaoModelOutput buscar(@PathVariable String codigoSubEstacao) {
		SubEstacao sub = subEstacaoService.buscarPorCodigo(codigoSubEstacao);
		return subEstacaoConverter.toModel(sub);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public SubEstacaoModelOutput adicionar(@Valid @RequestBody SubEstacaoModelInput subEstacaoInput) {		
		SubEstacao subSalvar = subEstacaoConverter.toDomainObject(subEstacaoInput);
		
		return subEstacaoConverter.toModel(subEstacaoService.salvar(subSalvar));
	}
	
	@PutMapping(value = "/{codigoSubEstacao}")
	@ResponseStatus(HttpStatus.OK)
	public SubEstacaoModelOutput alterar(@PathVariable String codigoSubEstacao, @Valid @RequestBody SubEstacaoModelInput subEstacaoInput) {		
		SubEstacao subEstacaoAtual = subEstacaoService.buscarPorCodigo(codigoSubEstacao);
		subEstacaoConverter.copyToDomainObject(subEstacaoInput, subEstacaoAtual);		
		
		return subEstacaoConverter.toModel(subEstacaoService.salvar(subEstacaoAtual));
	} 
	
	@DeleteMapping(value = "/{codigoSubEstacao}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable String codigoSubEstacao) {
		subEstacaoService.excluir(codigoSubEstacao);
	}
}
