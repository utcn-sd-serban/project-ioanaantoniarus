package ro.utcn.project.seed;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.project.entity.Book;
import ro.utcn.project.entity.User;
import ro.utcn.project.repository.api.BookRepository;
import ro.utcn.project.repository.api.RatingRepository;
import ro.utcn.project.repository.api.RepositoryFactory;
import ro.utcn.project.repository.api.UserRepository;

@Component
@RequiredArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE)
public class BookSeed implements CommandLineRunner {

    private final RepositoryFactory factory;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        BookRepository bookRepository=factory.createBookRepository();
        UserRepository repositoryUser=factory.createUserRepository();
        RatingRepository ratingRepository=factory.createRatingRepository();

        if(bookRepository.findAll().isEmpty()){
            Book book=bookRepository.save(new Book("1984","George Orwell","0452284236"));
            ratingRepository.createRating(book.getId());
        }

        if (repositoryUser.findAll().isEmpty()){
            repositoryUser.save(new User("ioana33", passwordEncoder.encode("ioana33"),"regular"));
            repositoryUser.save(new User("AnaBanana",passwordEncoder.encode("blabla"),"admin"));
            repositoryUser.save(new User("Andrei00",passwordEncoder.encode("0000"),"regular"));
        }
    }
}
