package br.com.zupacademy.guzzo.proposta.validator;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExisteIdValidator implements ConstraintValidator<ExisteId, Long> {

	private String atributo;
	private Class<?> entidade;

	@PersistenceContext
	private EntityManager em;

	@Override
	public void initialize(ExisteId params) {
		this.atributo = params.atributo();
		this.entidade = params.entidade();
	}

	@Override
	public boolean isValid(Long value, ConstraintValidatorContext context) {
		// adicionado para usar em atributos que não são obrigatorios
		if (value == null) {
			return true;
		}

		Query query = em.createQuery("select 1 from " + entidade.getName() + " where " + atributo + "=:value");
		query.setParameter("value", value);

		List<?> list = query.getResultList();

		return !list.isEmpty();
	}

}
