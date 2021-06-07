package br.com.zupacademy.guzzo.proposta.novaproposta;

import java.net.URI;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class NovaPropostaController {

	@PersistenceContext
	private EntityManager entityManager;

	@PostMapping("/propostas")
	@Transactional
	public ResponseEntity<?> cadastrarNovaProposta(@RequestBody @Valid NovaPropostaRequest request,
			UriComponentsBuilder builder) {
		Proposta proposta = request.converterParaProposta();
		entityManager.persist(proposta);

		URI enderecoConsulta = builder.path("/propostas/{id}").build(proposta.getId());

		return ResponseEntity.created(enderecoConsulta).build();

	}
}
