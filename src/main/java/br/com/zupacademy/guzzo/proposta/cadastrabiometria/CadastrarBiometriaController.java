package br.com.zupacademy.guzzo.proposta.cadastrabiometria;

import java.net.URI;

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
import br.com.zupacademy.guzzo.proposta.cadastracartao.CartaoRepository;

@RestController
public class CadastrarBiometriaController {

	@Autowired
	private ValidaFingerprintBase64Biometria validaFingerprintBase64Biometria;

	@Autowired
	private CartaoRepository cartaoRepository;

	@InitBinder
	public void init(WebDataBinder binder) {
		binder.addValidators(validaFingerprintBase64Biometria);
	}

	@PostMapping("/cartoes/{id}/biometria")
	public ResponseEntity<?> cadastra(@PathVariable String id, @RequestBody @Valid CadastroBiometriaRequest request,
			UriComponentsBuilder builder) {

		Cartao cartao = cartaoRepository.findById(id).get();

		if (cartao == null) {
			return ResponseEntity.notFound().build();
		}

		cartao.adicionaBiometria(request.converterParaBiometria(cartao));
		cartaoRepository.save(cartao);

		URI enderecoConsulta = builder.path("/cartoes/{id}/biometria/{idBiometria}").build(cartao.getId(),
				cartao.getUltimaBiometria().getId());

		return ResponseEntity.created(enderecoConsulta).build();
	}

}
