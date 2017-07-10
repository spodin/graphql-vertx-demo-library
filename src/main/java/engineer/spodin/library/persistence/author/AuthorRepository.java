package engineer.spodin.library.persistence.author;

public interface AuthorRepository {

    Author findById(Long authorId);
}
