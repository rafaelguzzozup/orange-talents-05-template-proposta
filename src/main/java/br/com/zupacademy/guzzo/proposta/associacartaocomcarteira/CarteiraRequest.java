package br.com.zupacademy.guzzo.proposta.associacartaocomcarteira;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.guzzo.proposta.cadastracartao.Cartao;
import br.com.zupacademy.guzzo.proposta.comunincasistemaexternocartao.VinculoCarteiraExternoRequest;

public class CarteiraRequest {

	@Email
	@NotBlank
	private String email;

	@NotNull
	private TipoCarteira carteira;

	public CarteiraRequest(@Email String email, @NotNull TipoCarteira carteira) {
		this.email = email;
		this.carteira = carteira;
	}

	public TipoCarteira getCarteira() {
		return carteira;
	}

	public String getEmail() {
		return email;
	}

	public VinculoCarteiraExternoRequest converterParaVinculoCarteiraExternoRequest() {
		return new VinculoCarteiraExternoRequest(this.email, this.carteira.toString());
	}

	public CarteiraDigital converterParaCarteiraDigital(Cartao cartao) {
		return new CarteiraDigital(email, carteira, cartao);
	}

}
