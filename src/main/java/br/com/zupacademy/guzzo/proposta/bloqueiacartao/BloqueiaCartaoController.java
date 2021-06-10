package br.com.zupacademy.guzzo.proposta.bloqueiacartao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.guzzo.proposta.cadastracartao.Cartao;

@RestController
public class BloqueiaCartaoController {

	@PersistenceContext
	private EntityManager entityManager;

	@PostMapping("/cartoes/{id}/bloquear")
	@Transactional
	public ResponseEntity<?> bloqueia(@PathVariable String id, HttpServletRequest request) {

		Cartao cartao = entityManager.find(Cartao.class, id);

		if (cartao == null) {
			return ResponseEntity.notFound().build();
		}

		if (cartao.cartaoBloqueado()) {
			return ResponseEntity.unprocessableEntity().build();
		}

		Bloqueio bloqueio = new Bloqueio(request.getRemoteAddr(), request.getHeader("User-Agent"), cartao);

		cartao.adicionaBloqueio(bloqueio);
		entityManager.persist(cartao);

		return ResponseEntity.ok().build();

	}
}
