package br.com.zupacademy.guzzo.proposta.acompanhaproposta;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.guzzo.proposta.metricas.MetricasPropostas;
import br.com.zupacademy.guzzo.proposta.novaproposta.Proposta;
import br.com.zupacademy.guzzo.proposta.novaproposta.PropostaRepository;

@RestController
public class AcompanhaPropostaController {

	@Autowired
	private MetricasPropostas metricasPropostas;

	@Autowired
	private PropostaRepository propostaRepository;

	@GetMapping("/propostas/{id}")
	public ResponseEntity<?> buscaPropostaPeloId(@PathVariable Long id) {
		
		Optional<Proposta> proposta = metricasPropostas.tempoConsultaProposta().record(() -> {
			return propostaRepository.findById(id);
		});

		if (proposta.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		AcompanhaPropostaDto acompanhaPropostaDto = new AcompanhaPropostaDto(proposta.get());

		return ResponseEntity.ok(acompanhaPropostaDto);
	}
}
