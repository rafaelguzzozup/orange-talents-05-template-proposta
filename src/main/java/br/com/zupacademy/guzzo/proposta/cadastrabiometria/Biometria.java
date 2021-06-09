package br.com.zupacademy.guzzo.proposta.cadastrabiometria;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import br.com.zupacademy.guzzo.proposta.cadastracartao.Cartao;

@Entity
public class Biometria {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private String id;

	@NotBlank
	@Lob
	private String fingerprint;

	@NotNull
	private LocalDateTime dataCadastro;

	@NotNull
	@ManyToOne
	private Cartao cartao;

	@Deprecated
	public Biometria() {

	}

	public Biometria(@NotBlank String fingerprint, @NotNull Cartao cartao) {
		this.fingerprint = fingerprint;
		this.cartao = cartao;
		this.dataCadastro = LocalDateTime.now();
	}

	public String getId() {
		return id;
	}

	public String getFingerprint() {
		return fingerprint;
	}

	public Cartao getCartao() {
		return cartao;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

}
