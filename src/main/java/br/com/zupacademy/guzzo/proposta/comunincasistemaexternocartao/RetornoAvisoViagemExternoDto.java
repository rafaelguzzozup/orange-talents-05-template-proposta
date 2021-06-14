package br.com.zupacademy.guzzo.proposta.comunincasistemaexternocartao;

public class RetornoAvisoViagemExternoDto {

	private String resultado;

	@Deprecated
	public RetornoAvisoViagemExternoDto() {

	}

	public RetornoAvisoViagemExternoDto(String resultado) {
		this.resultado = resultado;
	}

	public String getResultado() {
		return resultado;
	}

}
