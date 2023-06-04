package com.vinicius;

import com.vinicius.enums.Category;
import com.vinicius.model.Course;
import com.vinicius.model.Lesson;
import com.vinicius.repository.CourseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CrudSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(CourseRepository courseRepository) {
		return args -> {

			courseRepository.deleteAll();

			Course c = new Course();
			c.setName("Angular com Spring");
			c.setCategory(Category.FRONT_END);


			Lesson l = new Lesson();
			l.setName("Introdução");
			l.setYoutubeUrl("asdasd");
			l.setCourse(c);
			c.getLessons().add(l);

			courseRepository.save(c);
		};
	}

}
