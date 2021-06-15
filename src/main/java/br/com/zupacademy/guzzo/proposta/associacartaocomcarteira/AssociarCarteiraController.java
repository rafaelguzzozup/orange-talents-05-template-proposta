package br.com.zupacademy.guzzo.proposta.associacartaocomcarteira;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.guzzo.proposta.cadastracartao.Cartao;
import br.com.zupacademy.guzzo.proposta.cadastracartao.CartaoRepository;
import br.com.zupacademy.guzzo.proposta.comunincasistemaexternocartao.CartaoResourseFeign;
import feign.FeignException;

@RestController
public class AssociarCarteiraController {

	@Autowired
	private CartaoResourseFeign cartaoResourseFeign;

	@Autowired
	private CartaoRepository cartaoRepository;
	
	private final Logger logger = LoggerFactory.getLogger(AssociarCarteiraController.class);

	@PostMapping("/cartoes/{id}/carteiras")
	public ResponseEntity<?> associaCartaoComCarteira(@PathVariable String id,
			@RequestBody @Valid CarteiraRequest carteiraRequest, UriComponentsBuilder uriComponentsBuilder) {

		Optional<Cartao> possivelCartao = cartaoRepository.findById(id);

		if (possivelCartao.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		Cartao cartao = possivelCartao.get();
		
		if(cartao.jaExisteCarteiraAssociada(carteiraRequest.getCarteira())) {
			return ResponseEntity.unprocessableEntity().body("Cartão já vinculado a carteira informada!");
		}
		

		try {

			cartaoResourseFeign.associaCarteiraExterno(id, carteiraRequest.converterParaVinculoCarteiraExternoRequest());
			
			CarteiraDigital carteiraDigital = carteiraRequest.converterParaCarteiraDigital(cartao);
			cartao.adicionarCarteira(carteiraDigital);
			
			cartaoRepository.save(cartao);
			String idCarteiraAdicionada = cartao.getUltimaCarteiraAdicionada().getId();
			
			logger.info("Vinculado carteira={} ao Cartao={} com sucesso!", idCarteiraAdicionada, cartao.getId().substring(2, 5) + "***");
			
			URI uri = uriComponentsBuilder.path("/cartoes/{id}/carteiras/{idCarteira}").buildAndExpand(cartao.getId(),idCarteiraAdicionada).toUri();
			return ResponseEntity.created(uri).build();

		} catch (FeignException e) {
			logger.warn("Não foi possivel vincular a carteira ao cartao={}, erro={}", cartao.getId().substring(2, 5) + "***", e.getMessage());
			return ResponseEntity.unprocessableEntity().body("Não foi possivel vincular a carteira ao cartão!");
		}

	}

}
