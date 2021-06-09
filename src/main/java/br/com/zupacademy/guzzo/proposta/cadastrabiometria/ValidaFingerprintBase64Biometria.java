package br.com.zupacademy.guzzo.proposta.cadastrabiometria;

import java.util.Base64;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ValidaFingerprintBase64Biometria implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return CadastroBiometriaRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		CadastroBiometriaRequest request = (CadastroBiometriaRequest) target;

		String value = request.getFingerprint();

		try {
			byte[] decode = Base64.getDecoder().decode(value.getBytes());
			String mensagemDecodificada = new String(decode);

		} catch (Exception e) {
			errors.rejectValue("fingerprint", HttpStatus.BAD_REQUEST.name(),
					"O valor=" + value + " não é uma string base64 valida");
		}

	}

}
