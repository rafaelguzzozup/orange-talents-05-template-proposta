package br.com.zupacademy.guzzo.proposta.novaproposta;

import java.net.URI;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class NovaPropostaController {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private ProibeMaisDeUmaPropostaParaMesmoDocumentoValidator proibeMaisDeUmaPropostaParaMesmoDocumentoValidator;

	@PostMapping("/propostas")
	@Transactional
	public ResponseEntity<?> cadastrarNovaProposta(@RequestBody @Valid NovaPropostaRequest request,
			UriComponentsBuilder builder) {

		if (proibeMaisDeUmaPropostaParaMesmoDocumentoValidator.existeProposta(request)) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
					"Já existe proposta cadastrada para o documento " + request.getDocumento());
		}

		Proposta proposta = request.converterParaProposta();
		entityManager.persist(proposta);

		URI enderecoConsulta = builder.path("/propostas/{id}").build(proposta.getId());

		return ResponseEntity.created(enderecoConsulta).build();

	}
}
