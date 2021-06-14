package br.com.zupacademy.guzzo.proposta.comunincasistemaexternocartao;

import br.com.zupacademy.guzzo.proposta.novaproposta.Proposta;

public class PropostaCartaoRequest {
	private String documento;
	private String nome;
	private String idProposta;

	public PropostaCartaoRequest(Proposta proposta) {
		this.documento = proposta.getDocumento();
		this.nome = proposta.getNome();
		this.idProposta = proposta.getId().toString();
	}

	public String getDocumento() {
		return documento;
	}

	public String getNome() {
		return nome;
	}

	public String getIdProposta() {
		return idProposta;
	}

}
