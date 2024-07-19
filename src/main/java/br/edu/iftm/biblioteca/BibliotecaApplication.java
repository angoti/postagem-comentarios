package br.edu.iftm.biblioteca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.edu.iftm.biblioteca.domain.Post;
import br.edu.iftm.biblioteca.domain.PostComment;
import br.edu.iftm.biblioteca.repository.PostCommentRepository;
import br.edu.iftm.biblioteca.repository.PostRepository;

@SpringBootApplication
public class BibliotecaApplication implements CommandLineRunner {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private PostCommentRepository postCommentRepository;

	public static void main(String[] args) {
		SpringApplication.run(BibliotecaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// criando um post
		Post publicacao = new Post();
		publicacao.setTitle("Acontece neste sábado a festa junina do IFTM");
		postRepository.save(publicacao);
		
		// Criando alguns comentários
		PostComment comentario1 = new PostComment();
		comentario1.setReview("Opa! festa boa");
		postCommentRepository.save(comentario1);
		publicacao.addComment(comentario1);

		PostComment comentario2 = new PostComment();
		comentario2.setReview("Não perco por nada!");
		comentario2.setPost(publicacao);
		postCommentRepository.save(comentario2);
		
		postRepository.save(publicacao);
		
		System.out.println("\n------------------------------------------------\nPost post1");
		// mostrando os comentários do post p1
		System.out.println(publicacao.getTitle());
		publicacao.getComments().forEach(comentario -> {
			System.out.println("  - " + comentario.getReview());
		});

		// Buscando todos os posts e comentários do BD
		System.out.println("\n------------------------------------------------\nBuscando todos os posts e comentários do BD");

		Iterable<Post> posts = postRepository.findAll();
		posts.forEach(post -> {
			System.out.println(post.getTitle());
			post.getComments().forEach(comment -> {
				System.out.println("  - " + comment.getReview());
			});
		});
		System.out.println("\n------------------------------------------------\nfim");
	}

}
