package com.proxyseller.Blog;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BlogApplication implements CommandLineRunner {
//
//	@Autowired
//	private UserRepository userResitory;
//	@Autowired
//	private BlogRepository blogRepository;
//	@Autowired
//	private BlogLikesRepository blogLikesRepository;
//	@Autowired
//	private BlogUnlikesRepository blogUnlikesRepository;
	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		var user1 = User.builder()
//				.username("test1")
//				.password("Anho@1993")
//				.build();
//		var user2 = User.builder()
//				.username("test2")
//				.password("Anho@1993")
//				.build();
//		userResitory.save(user1);
//		userResitory.save(user2);
//
//		var blog = Blog.builder()
//				.title("title1")
//				.content("content1")
//				.author(user1)
//				.build();
//		var blog1 = Blog.builder()
//				.title("title2")
//				.content("content2")
//				.author(user2)
//				.build();
//
//		blogRepository.save(blog);
//		blogRepository.save(blog1);

	}
}
