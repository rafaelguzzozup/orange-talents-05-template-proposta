package br.com.zupacademy.guzzo.proposta.novaproposta.solicitacaoanaliseexterno;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "http://localhost:9999/api", name = "api-analise-financeira")
public interface SolicitacaoAnaliseExternoFeign {
	
	@PostMapping(value = "/solicitacao")
	ResultadoAnaliseDto solicitaAnalise(@RequestBody SolicitacaoAnaliseRequest request);
}
