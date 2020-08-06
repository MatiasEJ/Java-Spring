package guru.springframework.springwebapp.bootstrap;

import guru.springframework.springwebapp.model.Author;
import guru.springframework.springwebapp.model.Book;
import guru.springframework.springwebapp.model.Publisher;
import guru.springframework.springwebapp.model.repositories.AuthorRepository;
import guru.springframework.springwebapp.model.repositories.BookRepository;
import guru.springframework.springwebapp.model.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData(){
        //Eric
        Publisher publisher = new Publisher("Harper Collins", "calle falsa 123");
        publisher.setName("Harper Collins");
        publisherRepository.save(publisher);

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design","1234", publisher);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);



        authorRepository.save(eric);
        bookRepository.save(ddd);


        //Rod

        Author rod = new Author("Rod","Johnson");
        Book noEJB = new Book("J2EE Dev without ejB", "2344", publisher);
        rod.getBooks().add(noEJB);
        authorRepository.save(rod);
        bookRepository.save(noEJB);

    }
}
