package com.avaliacao.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avaliacao.domain.exception.NegocioException;
import com.avaliacao.domain.exception.SubEstacaoNaoEncontradaException;
import com.avaliacao.domain.model.SubEstacao;
import com.avaliacao.domain.repository.SubEstacaoRepository;

@Service
public class SubEstacaoService {
	
	@Autowired
	private SubEstacaoRepository subestacaoRepository;
	
	public List<SubEstacao> listar(){
		return subestacaoRepository.findAll();
	}
	
	public Optional<SubEstacao> buscarPorId(Integer idSubestacao) {
		return subestacaoRepository.findById(idSubestacao);
	}
	
	public SubEstacao buscarPorCodigo(String codigoSubEstacao) {
		return subestacaoRepository.findByCodigo(codigoSubEstacao).orElseThrow(() -> new SubEstacaoNaoEncontradaException(codigoSubEstacao));
	}
	
	public SubEstacao salvar(SubEstacao subEstacao) {
		Optional<SubEstacao> subExistente = subestacaoRepository.findByCodigo(subEstacao.getCodigo());
		
		boolean continua = false;
		if(!subExistente.isPresent()) {
			continua = true;
		}else if(subExistente.get().getIdSubestacao()==subEstacao.getIdSubestacao()) {
			continua = true;
		}
		
		if(continua) {
			if(subEstacao.getIdSubestacao()==null) {
				subEstacao.getRedesMt().forEach(redeMt ->{
					redeMt.setSubEstacao(subEstacao);
				});
			}
			SubEstacao sub = subestacaoRepository.save(subEstacao);
			return sub;
		} else {
			throw new NegocioException(String.format("O código '%s' já foi cadastrado para a SubEstação '%s'.",subExistente.get().getCodigo(), subExistente.get().getNome()));
		}
	}
	
	public void excluir(String codigoSubEstacao) {
		SubEstacao subExcluir = buscarPorCodigo(codigoSubEstacao);
		subestacaoRepository.delete(subExcluir);
	}
}
