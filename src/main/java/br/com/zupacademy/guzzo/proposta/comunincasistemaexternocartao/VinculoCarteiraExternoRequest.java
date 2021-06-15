package br.com.zupacademy.guzzo.proposta.comunincasistemaexternocartao;

public class VinculoCarteiraExternoRequest {

	private String email;
	private String carteira;

	public VinculoCarteiraExternoRequest(String email, String carteira) {
		this.email = email;
		this.carteira = carteira;
	}

	public String getEmail() {
		return email;
	}

	public String getCarteira() {
		return carteira;
	}

}
