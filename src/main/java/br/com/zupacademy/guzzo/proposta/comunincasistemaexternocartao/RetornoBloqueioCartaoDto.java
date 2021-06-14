package br.com.zupacademy.guzzo.proposta.comunincasistemaexternocartao;

public class RetornoBloqueioCartaoDto {

	private StatusCartao resultado;

	@Deprecated
	public RetornoBloqueioCartaoDto() {

	}

	public RetornoBloqueioCartaoDto(StatusCartao resultado) {
		this.resultado = resultado;
	}

	public StatusCartao getResultado() {
		return resultado;
	}

}
