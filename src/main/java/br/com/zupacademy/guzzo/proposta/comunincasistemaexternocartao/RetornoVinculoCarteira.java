package br.com.zupacademy.guzzo.proposta.comunincasistemaexternocartao;

public class RetornoVinculoCarteira {

	private String id;
	private String resultado;

	public RetornoVinculoCarteira(String id, String resultado) {
		this.id = id;
		this.resultado = resultado;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	@Override
	public String toString() {
		return "RetornoVinculoCarteira [id=" + id + ", resultado=" + resultado + "]";
	}

}
