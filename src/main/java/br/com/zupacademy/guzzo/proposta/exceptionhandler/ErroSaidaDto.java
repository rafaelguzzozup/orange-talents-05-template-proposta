package br.com.zupacademy.guzzo.proposta.exceptionhandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ErroSaidaDto {
	private String titulo;
	private LocalDateTime dataHora = LocalDateTime.now();
	private List<CampoErroDto> campos = new ArrayList<>();

	public ErroSaidaDto(String titulo) {
		this.titulo = titulo;
	}

	public void adicionarCampoComErro(String campo, String mensagem) {
		CampoErroDto campoErroDto = new CampoErroDto(campo, mensagem);
		this.campos.add(campoErroDto);
	}

	public List<CampoErroDto> getCampos() {
		return campos;
	}

	public String getTitulo() {
		return titulo;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

}
