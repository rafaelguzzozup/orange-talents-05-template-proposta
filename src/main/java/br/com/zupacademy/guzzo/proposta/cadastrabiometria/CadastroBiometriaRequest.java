package br.com.zupacademy.guzzo.proposta.cadastrabiometria;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.com.zupacademy.guzzo.proposta.cadastracartao.Cartao;

public class CadastroBiometriaRequest {

	@NotBlank
	private String fingerprint;

	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public CadastroBiometriaRequest(@NotBlank String fingerprint) {
		this.fingerprint = fingerprint;
	}

	public Biometria converterParaBiometria(Cartao cartao) {
		return new Biometria(fingerprint, cartao);
	}

	public String getFingerprint() {
		return fingerprint;
	}

}
