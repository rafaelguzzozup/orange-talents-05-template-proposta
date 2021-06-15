package br.com.zupacademy.guzzo.proposta.bloqueiacartao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.zupacademy.guzzo.proposta.cadastracartao.Cartao;
import br.com.zupacademy.guzzo.proposta.cadastracartao.CartaoRepository;
import br.com.zupacademy.guzzo.proposta.comunincasistemaexternocartao.BloqueioRequest;
import br.com.zupacademy.guzzo.proposta.comunincasistemaexternocartao.CartaoResourseFeign;
import br.com.zupacademy.guzzo.proposta.comunincasistemaexternocartao.StatusCartao;
import feign.FeignException;

/*
 * Classe criada para notificar o sistema legado que o usuario solicitou bloqueio do cartao; 
 *  
*/

@Component
public class NotificarSistemaLegadoBloqueioCartaoPeriodico {

	private final Logger logger = LoggerFactory.getLogger(NotificarSistemaLegadoBloqueioCartaoPeriodico.class);

	@Autowired
	private CartaoRepository cartaoRepository;

	@Autowired
	private CartaoResourseFeign cartaoResourseFeign;

	@Scheduled(fixedDelayString = "${periodicidade.notifica-bloqueio-cartao}")
	protected void atualizarStatusProposta() {

		List<Cartao> cartoesBloqueadosQueNaoNotificaramSistema = cartaoRepository.findByStatusIsNullAndBloqueioNotNull();

		if (cartoesBloqueadosQueNaoNotificaramSistema.isEmpty()) {
			logger.info("Nenhum cartão com bloqueio sem notificar o sistema legado!");
		}

		cartoesBloqueadosQueNaoNotificaramSistema.forEach(cartao -> {
			try {

				cartaoResourseFeign.notificaSistemaBloqueio(cartao.getId(), new BloqueioRequest("propostas"));
				cartao.adicionarStatus(StatusCartao.BLOQUEADO);

				cartaoRepository.save(cartao);

				logger.info(
						"Notificado sistema legado para bloqueio do Cartao={}  e alterado status para={}, com sucesso!",
						cartao.getId().substring(2, 5) + "***", cartao.getStatus());
			} catch (FeignException e) {

				logger.warn("Não foi possivel atualizar o status do cartao={}, erro={}",
						cartao.getId().substring(2, 5) + "***", e.getMessage());
			}

		});

	}

}
