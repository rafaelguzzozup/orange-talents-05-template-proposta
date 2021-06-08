package br.com.zupacademy.guzzo.proposta.cadastracartao.comunincasistemaexterno;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "${api.cartoes}", name = "api-cartoes")
public interface CartaoResourseFeign {

	@PostMapping(value = "/api/cartoes")
	RetornoCartaoResourceDto gerarNumeroCartaoProposta(@RequestBody PropostaCartaoRequest request);
}
