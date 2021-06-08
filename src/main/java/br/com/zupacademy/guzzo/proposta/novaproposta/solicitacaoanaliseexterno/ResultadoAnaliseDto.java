package br.com.zupacademy.guzzo.proposta.novaproposta.solicitacaoanaliseexterno;

public class ResultadoAnaliseDto {
	private String documento;
	private String nome;
	private String idProposta;
	private StatusSolicitacao resultadoSolicitacao;

	public ResultadoAnaliseDto(String documento, String nome, String idProposta,
			StatusSolicitacao resultadoSolicitacao) {
		this.documento = documento;
		this.nome = nome;
		this.idProposta = idProposta;
		this.resultadoSolicitacao = resultadoSolicitacao;
	}

	public String getDocumento() {
		return documento;
	}

	public String getNome() {
		return nome;
	}

	public String getIdProposta() {
		return idProposta;
	}

	public StatusSolicitacao getResultadoSolicitacao() {
		return resultadoSolicitacao;
	}

	@Override
	public String toString() {
		return "ResultadoAnaliseDto [documento=" + documento + ", nome=" + nome + ", idProposta=" + idProposta
				+ ", resultadoSolicitacao=" + resultadoSolicitacao + "]";
	}
	

}
