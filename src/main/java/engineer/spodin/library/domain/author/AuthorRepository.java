package engineer.spodin.library.domain.author;

public interface AuthorRepository {

    Author findById(Long authorId);
}
