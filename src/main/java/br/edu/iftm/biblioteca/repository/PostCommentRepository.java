package br.edu.iftm.biblioteca.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.edu.iftm.biblioteca.domain.PostComment;

@Repository
public interface PostCommentRepository extends CrudRepository<PostComment, Long> {
	
}
