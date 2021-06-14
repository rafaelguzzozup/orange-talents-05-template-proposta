package br.com.zupacademy.guzzo.proposta.metricas;

import org.springframework.stereotype.Component;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;

@Component
public class MetricasPropostas {

	private final MeterRegistry meterRegistry;

	public MetricasPropostas(MeterRegistry meterRegistry) {
		this.meterRegistry = meterRegistry;
	}

	public void contadorPropostaNaoElegivel() {
		contador("proposta_criada_nao_elegivel");
	}

	public void contadorPropostaElegivel() {
		contador("proposta_criada_elegivel");
	}
	
	public Timer tempoConsultaProposta() {
		return this.meterRegistry.timer("consultar_proposta");
	}

	private void contador(String nomeMetrica) {
		Counter contador = this.meterRegistry.counter(nomeMetrica);
		contador.increment();
	}

}
