package br.com.zupacademy.guzzo.proposta.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UnicoRegistroValidator.class)
public @interface UnicoRegistro {
	String message() default "Registro já cadastrado no banco de dados!";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	Class<?> entidade();

	String atributo();
}
