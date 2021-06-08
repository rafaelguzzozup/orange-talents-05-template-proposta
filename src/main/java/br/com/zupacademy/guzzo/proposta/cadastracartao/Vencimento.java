package br.com.zupacademy.guzzo.proposta.cadastracartao;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Vencimento {

	@Id
	private String id;
	private Integer dia;
	private LocalDateTime dataDeCriacao;

	@Deprecated
	public Vencimento() {

	}

	public Vencimento(String id, Integer dia, LocalDateTime dataDeCriacao) {
		this.id = id;
		this.dia = dia;
		this.dataDeCriacao = dataDeCriacao;
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
