package br.com.zupacademy.guzzo.proposta.comunincasistemaexternocartao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.zupacademy.guzzo.proposta.cadastracartao.Cartao;
import br.com.zupacademy.guzzo.proposta.novaproposta.Proposta;
import br.com.zupacademy.guzzo.proposta.validator.UnicoRegistro;

public class RetornoCartaoResourceDto {

	@UnicoRegistro(entidade = Cartao.class, atributo = "id")
	private String id;
	private LocalDateTime emitidoEm;
	private String titular;
	private BigDecimal limite;
	private Long idProposta;
	private VencimentoCartaoDto vencimento;

	public RetornoCartaoResourceDto(String id, LocalDateTime emitidoEm, String titular, BigDecimal limite,
			Long idProposta, VencimentoCartaoDto vencimento) {
		this.id = id;
		this.emitidoEm = emitidoEm;
		this.titular = titular;
		this.limite = limite;
		this.idProposta = idProposta;
		this.vencimento = vencimento;
	}

	public Cartao converterParaCartao(Proposta proposta) {
		return new Cartao(id, emitidoEm, titular, limite, vencimento.converterParaVencimento(), proposta);
	}

	public String getId() {
		return id;
	}

	public LocalDateTime getEmitidoEm() {
		return emitidoEm;
	}

	public String getTitular() {
		return titular;
	}

	public BigDecimal getLimite() {
		return limite;
	}

	public Long getIdProposta() {
		return idProposta;
	}

	public VencimentoCartaoDto getVencimento() {
		return vencimento;
	}

}
