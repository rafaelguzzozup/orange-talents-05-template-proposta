package br.com.zupacademy.guzzo.proposta.novaproposta;

import java.math.BigDecimal;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.keygen.KeyGenerators;

import br.com.zupacademy.guzzo.proposta.seguranca.Criptografia;
import br.com.zupacademy.guzzo.proposta.validator.CPFouCNPJ;

public class NovaPropostaRequest {

	@NotBlank
	@CPFouCNPJ
	private String documento;

	@Email
	@NotBlank
	private String email;

	@NotBlank
	private String nome;

	@NotBlank
	private String endereco;

	@NotNull
	@PositiveOrZero
	private BigDecimal salario;

	public NovaPropostaRequest(@NotBlank String documento, @Email @NotBlank String email, @NotBlank String nome,
			@NotBlank String endereco, @NotNull @PositiveOrZero BigDecimal salario) {
		this.documento = documento;
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
	}

	public Proposta converterParaProposta() {

		return new Proposta(Criptografia.encrypt(documento), email, nome, endereco, salario);
	}

	public String getDocumento() {
		return documento;
	}

	public String getEmail() {
		return email;
	}

}
