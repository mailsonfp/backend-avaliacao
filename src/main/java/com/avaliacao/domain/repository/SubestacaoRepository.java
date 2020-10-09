package com.avaliacao.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.avaliacao.domain.model.Subestacao;

@Repository
public interface SubestacaoRepository extends JpaRepository<Subestacao, Integer> {

}
