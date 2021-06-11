package br.com.zupacademy.guzzo.proposta.cadastracartao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartaoRepository extends JpaRepository<Cartao, String> {
	
	List<Cartao> findByStatusIsNullAndBloqueioNotNull();

}
