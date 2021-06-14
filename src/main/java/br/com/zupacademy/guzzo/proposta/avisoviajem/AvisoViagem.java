package br.com.zupacademy.guzzo.proposta.avisoviajem;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import br.com.zupacademy.guzzo.proposta.cadastracartao.Cartao;

@Entity
public class AvisoViagem {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private String id;

	@NotBlank
	private String destino;

	@NotNull
	@Future
	private LocalDate dataTerminoViagem;

	private LocalDateTime dataCriacaoAviso;

	@NotNull
	private String remoteAddr;

	@NotNull
	private String userAgent;

	@Valid
	@NotNull
	@ManyToOne
	private Cartao cartao;

	@Deprecated
	public AvisoViagem() {

	}

	public AvisoViagem(@NotBlank String destino, @NotNull @Future LocalDate dataTerminoViagem, String remoteAddr,
			String userAgent, Cartao cartao) {
		this.destino = destino;
		this.dataTerminoViagem = dataTerminoViagem;
		this.remoteAddr = remoteAddr;
		this.userAgent = userAgent;
		this.cartao = cartao;
		this.dataCriacaoAviso = LocalDateTime.now();
	}

	public String getDestino() {
		return destino;
	}

	public LocalDate getDataTerminoViagem() {
		return dataTerminoViagem;
	}

	public String getRemoteAddr() {
		return remoteAddr;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public Cartao getCartao() {
		return cartao;
	}

	public LocalDateTime getDataCriacaoAviso() {
		return dataCriacaoAviso;
	}

}
