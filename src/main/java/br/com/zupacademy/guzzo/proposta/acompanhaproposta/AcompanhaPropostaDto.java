package br.com.zupacademy.guzzo.proposta.acompanhaproposta;

import java.math.BigDecimal;

import br.com.zupacademy.guzzo.proposta.novaproposta.Proposta;
import br.com.zupacademy.guzzo.proposta.novaproposta.StatusProposta;

public class AcompanhaPropostaDto {

	private Long id;
	private String documento;
	private String email;
	private String nome;
	private String endereco;
	private BigDecimal salario;
	private String cartao;
	private StatusProposta status;

	public AcompanhaPropostaDto(Proposta proposta) {

		this.id = proposta.getId();
		this.documento = proposta.getDocumento();
		this.email = proposta.getEmail();
		this.nome = proposta.getNome();
		this.endereco = proposta.getEndereco();
		this.salario = proposta.getSalario();
		this.status = proposta.getStatus();

		if (proposta.getCartao() != null) {
			this.cartao = proposta.getCartao().getId();
		}

	}

	public Long getId() {
		return id;
	}

	public String getDocumento() {
		return documento;
	}

	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public String getCartao() {
		return cartao;
	}

	public StatusProposta getStatus() {
		return status;
	}

}
