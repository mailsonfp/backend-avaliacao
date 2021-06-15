package com.avaliacao.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.avaliacao.domain.model.SubEstacao;

@Repository
public interface SubEstacaoRepository extends JpaRepository<SubEstacao, Integer> {
	
	public Optional<SubEstacao> findByCodigo(String codigo);
}
