package br.com.zupacademy.guzzo.proposta.novaproposta;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {
	List<Proposta> findByStatusAndCartaoIsNull(StatusProposta status);
}
