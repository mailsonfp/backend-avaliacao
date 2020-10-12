package com.avaliacao.api.model.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.avaliacao.api.model.input.SubEstacaoModelInput;
import com.avaliacao.api.model.output.SubEstacaoModelOutput;
import com.avaliacao.domain.model.RedeMt;
import com.avaliacao.domain.model.SubEstacao;

@Component
public class SubEstacaoModelConverter {
	
	@Autowired
    private ModelMapper modelMapper;
	
	public SubEstacaoModelOutput toModel(SubEstacao subestacao) {
		return modelMapper.map(subestacao, SubEstacaoModelOutput.class);
	}
	
	public List<SubEstacaoModelOutput> toCollectionModel(List<SubEstacao> subestacoes){
		return subestacoes.stream()
				.map(subestacao -> toModel(subestacao))
				.collect(Collectors.toList());
	}
	
	public SubEstacao toDomainObject(SubEstacaoModelInput subEstacaoInput) {
		 return modelMapper.map(subEstacaoInput, SubEstacao.class);
	 }
   
	 public void copyToDomainObject(SubEstacaoModelInput subEstacaoInput, SubEstacao subEstacao) {
		 
		 subEstacao.getRedesMt().forEach(redeMt ->{
				redeMt.setSubEstacao(null);
			});
		
		 subEstacao.setNome(subEstacaoInput.getNome());
		 subEstacao.setLatitude(subEstacaoInput.getLatitude());
		 subEstacao.setLongitude(subEstacaoInput.getLongitude());
		 subEstacaoInput.getRedesMt().forEach(redeMt ->{
			RedeMt rede = new RedeMt();
			
			rede.setCodigo(redeMt.getCodigo());
			rede.setNome(redeMt.getNome());
			rede.setTensao_nominal(redeMt.getTensao_nominal());
			rede.setSubEstacao(subEstacao);
			
			subEstacao.getRedesMt().add(rede);
		 });
		 
		 subEstacao.getRedesMt().removeIf(redemt -> redemt.getSubEstacao()==null);	 
	 }
}
