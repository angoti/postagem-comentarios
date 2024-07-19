package br.edu.iftm.biblioteca.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	/**
	 * explique o que é CascadeType.ALL e o que acontece se ele for removido
	 * 
	 * CascadeType.ALL é uma forma de definir o comportamento de um relacionamento
	 * de
	 * entidade. Neste caso, quando um Post é removido, todos os PostComment
	 * associados a ele também serão removidos.
	 *
	 */
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<PostComment> comments = new ArrayList<>();

	public void addComment(PostComment comment) {
		comments.add(comment);
		comment.setPost(this);
	}

	public void removeComment(PostComment comment) {
		comments.remove(comment);
		comment.setPost(null);
	}
}
