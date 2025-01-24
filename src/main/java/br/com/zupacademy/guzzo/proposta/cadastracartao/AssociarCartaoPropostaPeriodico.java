package br.com.zupacademy.guzzo.proposta.cadastracartao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.zupacademy.guzzo.proposta.comunincasistemaexternocartao.CartaoResourseFeign;
import br.com.zupacademy.guzzo.proposta.comunincasistemaexternocartao.PropostaCartaoRequest;
import br.com.zupacademy.guzzo.proposta.comunincasistemaexternocartao.RetornoCartaoResourceDto;
import br.com.zupacademy.guzzo.proposta.novaproposta.Proposta;
import br.com.zupacademy.guzzo.proposta.novaproposta.PropostaRepository;
import br.com.zupacademy.guzzo.proposta.novaproposta.StatusProposta;
import feign.FeignException;
import io.opentracing.Span;
import io.opentracing.Tracer;

@Component
public class AssociarCartaoPropostaPeriodico {

	@Autowired
	private PropostaRepository propostaRepository;

	@Autowired
	private CartaoResourseFeign cartaoResourseFeign;

	private final Logger logger = LoggerFactory.getLogger(AssociarCartaoPropostaPeriodico.class);
	
	private final Tracer tracer;
	
	public AssociarCartaoPropostaPeriodico(Tracer tracer) {
		this.tracer = tracer;
	}

	@Scheduled(fixedDelayString = "${periodicidade.associa-cartao-proposta}")
	protected void associar() {

		List<Proposta> propostasSemCartao = propostaRepository.findByStatusAndCartaoIsNull(StatusProposta.ELEGIVEL);

		if (propostasSemCartao.isEmpty()) {
			logger.info("Não encontrado propostas elegiveis sem cartão!");
		}

		propostasSemCartao.forEach(proposta -> {
			Span spanAtivo = tracer.activeSpan();
			spanAtivo.setBaggageItem("associar-cartao-proposta.emal", proposta.getEmail());
			
			try {

				RetornoCartaoResourceDto cartaoDto = cartaoResourseFeign
						.gerarNumeroCartaoProposta(new PropostaCartaoRequest(proposta));

				Cartao cartao = cartaoDto.converterParaCartao(proposta);
				proposta.adicionaCartao(cartao);

				propostaRepository.save(proposta);

				logger.info("Cartão={} associado a Proposta={} com sucesso!", cartaoDto.getId().substring(2, 5) + "***",
						proposta.getId());
			} catch (FeignException e) {
				logger.warn("Não foi possivel adicionar o cartão a proposta={}, erro={}", proposta.getId(),
						e.getMessage());
			}

		});

	}
}
