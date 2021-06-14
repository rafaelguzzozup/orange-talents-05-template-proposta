package br.com.zupacademy.guzzo.proposta.comunincasistemaexternocartao;

import java.time.LocalDateTime;

import br.com.zupacademy.guzzo.proposta.cadastracartao.Vencimento;
import br.com.zupacademy.guzzo.proposta.validator.UnicoRegistro;

public class VencimentoCartaoDto {

	@UnicoRegistro(entidade = Vencimento.class, atributo = "id")
	private String id;
	private Integer dia;
	private LocalDateTime dataDeCriacao;

	public VencimentoCartaoDto(String id, Integer dia, LocalDateTime dataDeCriacao) {
		this.id = id;
		this.dia = dia;
		this.dataDeCriacao = dataDeCriacao;
	}

	public Vencimento converterParaVencimento() {
		return new Vencimento(id, dia, dataDeCriacao);
	}

	public String getId() {
		return id;
	}

	public Integer getDia() {
		return dia;
	}

	public LocalDateTime getDataDeCriacao() {
		return dataDeCriacao;
	}

}
