package br.com.zupacademy.guzzo.proposta.cadastracartao.comunincasistemaexterno;

public class BloqueioRequest {
	private String sistemaResponsavel;

	public BloqueioRequest(String sistemaResponsavel) {
		this.sistemaResponsavel = sistemaResponsavel;
	}

	public String getSistemaResponsavel() {
		return sistemaResponsavel;
	}

}
