package br.com.zupacademy.guzzo.proposta.novaproposta;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.zupacademy.guzzo.proposta.novaproposta.solicitacaoanaliseexterno.SolicitacaoAnaliseExternoFeign;
import br.com.zupacademy.guzzo.proposta.novaproposta.solicitacaoanaliseexterno.SolicitacaoAnaliseRequest;
import feign.FeignException;

/*
 * Classe criada para atualizar os status das propostas quando o serviço de analise financeira estiver fora; 
 *  
*/

@Component
public class AtualizarStatusPropostaPeriodico {

	private final Logger logger = LoggerFactory.getLogger(AtualizarStatusPropostaPeriodico.class);

	@Autowired
	private PropostaRepository propostaRepository;

	@Autowired
	private SolicitacaoAnaliseExternoFeign analiseExterna;

	@Scheduled(fixedDelayString = "${periodicidade.atualiza-status-proposta}")
	protected void atualizarStatusProposta() {

		List<Proposta> propostasSemCartao = propostaRepository.findByStatusAndCartaoIsNull(StatusProposta.NAO_ELEGIVEL);

		if (propostasSemCartao.isEmpty()) {
			logger.info("Não encontrado propostas com status não elegivel!");
		}

		propostasSemCartao.forEach(proposta -> {
			try {

				analiseExterna.solicitaAnalise(new SolicitacaoAnaliseRequest(proposta));
				proposta.adicionaStatus(StatusProposta.ELEGIVEL);

				propostaRepository.save(proposta);

				logger.info("Proposta={} com documento={} atualizada status para={}, com sucesso!", proposta.getId(),
						proposta.getDocumento().substring(2, 5) + "***", proposta.getStatus());
			} catch (FeignException e) {

				logger.warn("Não foi possivel atualizar o status da proposta={}, erro={}", proposta.getId(),
						e.getMessage());
			}

		});

	}

}
