package br.com.zupacademy.guzzo.proposta.avisoviajem;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.guzzo.proposta.cadastracartao.Cartao;
import br.com.zupacademy.guzzo.proposta.comunincasistemaexternocartao.AvisoViagemExternoRequest;
import br.com.zupacademy.guzzo.proposta.comunincasistemaexternocartao.CartaoResourseFeign;
import feign.FeignException;

@RestController
public class AvisoViagemController {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private CartaoResourseFeign cartaoResourseFeign;

	private final Logger logger = LoggerFactory.getLogger(AvisoViagemController.class);

	@PostMapping("/cartoes/{id}/viagem")
	@Transactional
	public ResponseEntity<?> cadastraAvisoViagem(@PathVariable String id,
			@RequestBody @Valid AvisoViagemRequest avisoViagemRequest, HttpServletRequest request) {

		Cartao cartao = entityManager.find(Cartao.class, id);

		if (cartao == null) {
			return ResponseEntity.notFound().build();
		}

		try {

			cartaoResourseFeign.notificaSistemaAvisoViagem(id,
					avisoViagemRequest.converterParaAvisoViagemExternoRequest());

			AvisoViagem avisoViagem = avisoViagemRequest.converterParaAvisoViagem(request, cartao);

			cartao.adicionaAvisoViagem(avisoViagem);
			entityManager.persist(cartao);

			logger.info("Notificado sistema externo sobre aviso viagem do Cartao={} com sucesso!",
					cartao.getId().substring(2, 5) + "***");

			return ResponseEntity.ok().build();

		} catch (FeignException e) {

			logger.warn("Não foi possivel notificar o sistema externo de viagem para o cartao={}, erro={}",
					cartao.getId().substring(2, 5) + "***", e.getMessage());

			return ResponseEntity.unprocessableEntity().body("Não foi possivel notificar o sistema externo!");
		}

	}

}
