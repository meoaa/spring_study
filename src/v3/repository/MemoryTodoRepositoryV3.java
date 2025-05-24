package v3.repository;

import domain.Todo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MemoryTodoRepositoryV3 implements TodoRepositoryV3 {

    private final static ArrayList<Todo> store = new ArrayList<>();
    @Override
    public void save(Todo todo) {
        store.add(todo);
    }

    @Override
    public List<Todo> findByTitle(String title) {
        return store.stream().filter(todo -> todo.getTitle().contains(title)).toList();

    }

    @Override
    public Optional<Todo> findById(int id) {
        return store.stream().filter(todo -> todo.getId() == id).findFirst();
    }

    @Override
    public List<Todo> findAll() {
        return store;
    }

    @Override
    public void remove(Todo todo) {
        store.remove(todo);
    }
}
