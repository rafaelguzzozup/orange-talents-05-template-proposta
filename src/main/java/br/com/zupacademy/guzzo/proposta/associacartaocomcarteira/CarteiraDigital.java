package br.com.zupacademy.guzzo.proposta.associacartaocomcarteira;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import br.com.zupacademy.guzzo.proposta.cadastracartao.Cartao;

@Entity
public class CarteiraDigital {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private String id;

	@Email
	@NotBlank
	private String email;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoCarteira carteira;

	@NotNull
	@ManyToOne
	private Cartao cartao;

	@Deprecated
	public CarteiraDigital() {

	}

	public CarteiraDigital(@Email @NotBlank String email, @NotNull TipoCarteira carteira, Cartao cartao) {
		this.email = email;
		this.carteira = carteira;
		this.cartao = cartao;
	}

	public String getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public TipoCarteira getCarteira() {
		return carteira;
	}

	public Cartao getCartao() {
		return cartao;
	}

}
