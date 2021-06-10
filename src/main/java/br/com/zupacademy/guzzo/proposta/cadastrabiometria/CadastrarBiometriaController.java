package br.com.zupacademy.guzzo.proposta.cadastrabiometria;

import java.net.URI;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.guzzo.proposta.cadastracartao.Cartao;

@RestController
public class CadastrarBiometriaController {

	@Autowired
	private ValidaFingerprintBase64Biometria validaFingerprintBase64Biometria;

	@PersistenceContext
	private EntityManager entityManager;

	@InitBinder
	public void init(WebDataBinder binder) {
		binder.addValidators(validaFingerprintBase64Biometria);
	}

	@PostMapping("/cartoes/{id}/biometria")
	@Transactional
	public ResponseEntity<?> cadastra(@PathVariable String id, @RequestBody @Valid CadastroBiometriaRequest request,
			UriComponentsBuilder builder) {

		Cartao cartao = entityManager.find(Cartao.class, id);

		if (cartao == null) {
			return ResponseEntity.notFound().build();
		}

		cartao.adicionaBiometria(request.converterParaBiometria(cartao));
		entityManager.persist(cartao);

		URI enderecoConsulta = builder.path("/cartoes/{id}/biometria/{idBiometria}").build(cartao.getId(),
				cartao.getUltimaBiometria().getId());

		return ResponseEntity.created(enderecoConsulta).build();
	}

}
