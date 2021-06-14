package br.com.zupacademy.guzzo.proposta.avisoviajem;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.guzzo.proposta.cadastracartao.Cartao;

@RestController
public class AvisoViagemController {

	@PersistenceContext
	private EntityManager entityManager;

	@PostMapping("/cartoes/{id}/viagem")
	@Transactional
	public ResponseEntity<?> cadastraAvisoViagem(@PathVariable String id,
			@RequestBody @Valid AvisoViagemRequest avisoViagemRequest, HttpServletRequest request) {

		Cartao cartao = entityManager.find(Cartao.class, id);

		if (cartao == null) {
			return ResponseEntity.notFound().build();
		}
		AvisoViagem avisoViagem = avisoViagemRequest.converterParaAvisoViagem(request, cartao);

		cartao.adicionaAvisoViagem(avisoViagem);
		entityManager.persist(cartao);

		return ResponseEntity.ok().build();
	}

}
