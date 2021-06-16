package br.com.zupacademy.guzzo.proposta.novaproposta;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import br.com.zupacademy.guzzo.proposta.seguranca.Criptografia;

@Component
public class ProibeMaisDeUmaPropostaParaMesmoDocumentoValidator {

	@PersistenceContext
	private EntityManager entityManager;

	public boolean existeProposta(NovaPropostaRequest request) {
		String documento = Criptografia.encrypt(request.getDocumento());

		Query query = entityManager
				.createQuery("select 1 from " + Proposta.class.getName() + " where documento = :documento");

		query.setParameter("documento", documento);
		if (query.getResultList().size() >= 1) {
			return true;
		}

		return false;
	}

}
