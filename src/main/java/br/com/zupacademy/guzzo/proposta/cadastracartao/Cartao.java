package br.com.zupacademy.guzzo.proposta.cadastracartao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import br.com.zupacademy.guzzo.proposta.avisoviajem.AvisoViagem;
import br.com.zupacademy.guzzo.proposta.bloqueiacartao.Bloqueio;
import br.com.zupacademy.guzzo.proposta.cadastrabiometria.Biometria;
import br.com.zupacademy.guzzo.proposta.comunincasistemaexternocartao.StatusCartao;
import br.com.zupacademy.guzzo.proposta.novaproposta.Proposta;

@Entity
public class Cartao {

	@Id
	@Column(unique = true)
	private String id;
	private LocalDateTime emitidoEm;
	private String titular;
	private BigDecimal limite;

	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "vencimento_id")
	private Vencimento vencimento;

	@OneToOne(mappedBy = "cartao")
	private Proposta proposta;

	@OneToMany(mappedBy = "cartao", cascade = CascadeType.ALL)
	private List<Biometria> biometrias = new ArrayList<>();

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "bloqueio_id")
	private Bloqueio bloqueio;

	@Enumerated(EnumType.STRING)
	private StatusCartao status;

	@OneToMany(mappedBy = "cartao", cascade = CascadeType.ALL)
	private List<AvisoViagem> avisoViagem = new ArrayList<>();;

	@Deprecated
	public Cartao() {

	}

	public Cartao(String id, LocalDateTime emitidoEm, String titular, BigDecimal limite, Vencimento vencimento,
			Proposta proposta) {
		this.id = id;
		this.emitidoEm = emitidoEm;
		this.titular = titular;
		this.limite = limite;
		this.vencimento = vencimento;
		this.proposta = proposta;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LocalDateTime getEmitidoEm() {
		return emitidoEm;
	}

	public void setEmitidoEm(LocalDateTime emitidoEm) {
		this.emitidoEm = emitidoEm;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public BigDecimal getLimite() {
		return limite;
	}

	public void setLimite(BigDecimal limite) {
		this.limite = limite;
	}

	public Vencimento getVencimento() {
		return vencimento;
	}

	public void setVencimento(Vencimento vencimento) {
		this.vencimento = vencimento;
	}

	public List<Biometria> getBiometrias() {
		return biometrias;
	}

	public void adicionaBiometria(Biometria biometria) {
		this.biometrias.add(biometria);
	}

	public Biometria getUltimaBiometria() {
		return this.biometrias.get(this.biometrias.size() - 1);
	}

	public Bloqueio getBloqueio() {
		return bloqueio;
	}

	public void adicionaBloqueio(Bloqueio bloqueio) {
		this.bloqueio = bloqueio;
	}

	public boolean cartaoBloqueado() {
		return this.bloqueio != null;
	}

	public void adicionarStatus(StatusCartao status) {
		this.status = status;
	}

	public StatusCartao getStatus() {
		return status;
	}

	public void adicionaAvisoViagem(AvisoViagem avisoViagem) {
		this.avisoViagem.add(avisoViagem);
	}
}
