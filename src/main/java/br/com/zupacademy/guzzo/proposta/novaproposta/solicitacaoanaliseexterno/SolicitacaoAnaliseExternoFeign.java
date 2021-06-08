package br.com.zupacademy.guzzo.proposta.novaproposta.solicitacaoanaliseexterno;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "${api.analise.financeira}", name = "api-analise-financeira")
public interface SolicitacaoAnaliseExternoFeign {
	
	@PostMapping(value = "/api/solicitacao")
	ResultadoAnaliseDto solicitaAnalise(@RequestBody SolicitacaoAnaliseRequest request);
}
