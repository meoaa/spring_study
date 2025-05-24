package v2.repository;

import domain.Todo;

import java.util.List;
import java.util.Optional;

public interface TodoRepositoryV2 {

    void save(Todo todo);

    List<Todo> findByTitle(String title);

    Optional<Todo> findById(int id);

    List<Todo> findAll();

    void remove(Todo todo);
}
