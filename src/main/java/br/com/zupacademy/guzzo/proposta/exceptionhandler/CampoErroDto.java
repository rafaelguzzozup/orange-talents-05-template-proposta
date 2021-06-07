package br.com.zupacademy.guzzo.proposta.exceptionhandler;

public class CampoErroDto {
	private String nome;
	private String mensagem;

	public CampoErroDto(String nome, String mensagem) {
		this.nome = nome;
		this.mensagem = mensagem;
	}

	public String getNome() {
		return nome;
	}

	public String getMensagem() {
		return mensagem;
	}
}
