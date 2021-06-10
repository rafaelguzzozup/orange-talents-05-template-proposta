package br.com.zupacademy.guzzo.proposta.bloqueiacartao;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.guzzo.proposta.cadastracartao.Cartao;

@Entity
public class Bloqueio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDateTime dataBloqueio;

	@NotBlank
	private String ipCliente;

	@NotBlank
	private String userAgentCliente;

	@NotNull
	@OneToOne(mappedBy = "bloqueio")
	private Cartao cartao;

	@Deprecated
	public Bloqueio() {

	}

	public Bloqueio(@NotBlank String ipCliente, @NotBlank String userAgentCliente, @NotNull Cartao cartao) {
		this.dataBloqueio = LocalDateTime.now();
		this.ipCliente = ipCliente;
		this.userAgentCliente = userAgentCliente;
		this.cartao = cartao;
	}

	public Long getId() {
		return id;
	}

	public LocalDateTime getDataBloqueio() {
		return dataBloqueio;
	}

	public String getIpCliente() {
		return ipCliente;
	}

	public String getUserAgentCliente() {
		return userAgentCliente;
	}

	public Cartao getCartao() {
		return cartao;
	}

}
