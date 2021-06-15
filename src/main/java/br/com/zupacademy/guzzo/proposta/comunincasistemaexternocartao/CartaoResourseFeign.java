package br.com.zupacademy.guzzo.proposta.comunincasistemaexternocartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "${api.cartoes}", name = "api-cartoes")
public interface CartaoResourseFeign {

	@PostMapping(value = "/api/cartoes")
	RetornoCartaoResourceDto gerarNumeroCartaoProposta(@RequestBody PropostaCartaoRequest request);
	
	@PostMapping(value = "/api/cartoes/{id}/bloqueios")
	RetornoBloqueioCartaoDto notificaSistemaBloqueio(@PathVariable String id, @RequestBody BloqueioRequest request);
	
	@PostMapping(value = "/api/cartoes/{id}/avisos")
	RetornoAvisoViagemExternoDto notificaSistemaAvisoViagem(@PathVariable String id, @RequestBody AvisoViagemExternoRequest request);
	
	@PostMapping(value = "/api/cartoes/{id}/carteiras")
	RetornoVinculoCarteira associaCarteiraExterno(@PathVariable String id, @RequestBody VinculoCarteiraExternoRequest request);
}
