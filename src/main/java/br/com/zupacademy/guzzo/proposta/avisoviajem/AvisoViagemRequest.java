package br.com.zupacademy.guzzo.proposta.avisoviajem;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.zupacademy.guzzo.proposta.cadastracartao.Cartao;

public class AvisoViagemRequest {

	@NotBlank
	private String destino;

	@NotNull
	@Future
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate dataTerminoViagem;

	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public AvisoViagemRequest(@NotBlank String destino) {
		this.destino = destino;
	}

	public void setDataTerminoViagem(LocalDate dataTerminoViagem) {
		this.dataTerminoViagem = dataTerminoViagem;
	}

	public AvisoViagem converterParaAvisoViagem(HttpServletRequest request, Cartao cartao) {
		return new AvisoViagem(destino, dataTerminoViagem, request.getRemoteAddr(), request.getHeader("User-Agent"), cartao);
	}

}
