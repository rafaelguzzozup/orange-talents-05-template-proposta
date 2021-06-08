package br.com.zupacademy.guzzo.proposta.novaproposta;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.guzzo.proposta.novaproposta.solicitacaoanaliseexterno.SolicitacaoAnaliseExternoFeign;
import br.com.zupacademy.guzzo.proposta.novaproposta.solicitacaoanaliseexterno.SolicitacaoAnaliseRequest;
import feign.FeignException;

@RestController
public class NovaPropostaController {

	@Autowired
	private PropostaRepository propostaRepository;

	@Autowired
	private ProibeMaisDeUmaPropostaParaMesmoDocumentoValidator proibeMaisDeUmaPropostaParaMesmoDocumentoValidator;

	@Autowired
	private SolicitacaoAnaliseExternoFeign analiseExterna;

	@PostMapping("/propostas")
	public ResponseEntity<?> cadastrarNovaProposta(@RequestBody @Valid NovaPropostaRequest request,
			UriComponentsBuilder builder) {

		if (proibeMaisDeUmaPropostaParaMesmoDocumentoValidator.existeProposta(request)) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
					"Já existe proposta cadastrada para o documento " + request.getDocumento());
		}

		Proposta proposta = request.converterParaProposta();
		propostaRepository.save(proposta);

		try {
			analiseExterna.solicitaAnalise(new SolicitacaoAnaliseRequest(proposta));
			proposta.adicionaStatus(StatusProposta.ELEGIVEL);

		} catch (FeignException e) {
			proposta.adicionaStatus(StatusProposta.NAO_ELEGIVEL);
		}

		propostaRepository.save(proposta);

		URI enderecoConsulta = builder.path("/propostas/{id}").build(proposta.getId());

		return ResponseEntity.created(enderecoConsulta).build();

	}
}
