package br.com.zupacademy.guzzo.proposta.comunincasistemaexternocartao;

import java.time.LocalDate;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AvisoViagemExternoRequest {

	@NotBlank
	private String destino;

	@NotNull
	@Future
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate validoAte;

	public AvisoViagemExternoRequest(@NotBlank String destino, @NotNull @Future LocalDate validoAte) {
		this.destino = destino;
		this.validoAte = validoAte;
	}

	public String getDestino() {
		return destino;
	}

	public LocalDate getValidoAte() {
		return validoAte;
	}

}
