package v3.service;

import domain.Todo;

import v3.repository.TodoRepositoryV3;

import java.util.List;
import java.util.function.Supplier;

public class TodoServiceImplV3 implements TodoServiceV3{

    private final TodoRepositoryV3 repository;

    /**
     * 생성자를 통해 외부에서 구현체를 전달받음.
     */
    public TodoServiceImplV3(TodoRepositoryV3 repository) {
        this.repository = repository;
    }

    @Override
    public void addTodo(Todo todo) {
        repository.save(todo);
    }

    @Override
    public Todo searchById(int id) {
        return repository.findById(id).orElseThrow(noTodoException());
    }


    @Override
    public List<Todo> searchByTitle(String search) {
        return repository.findByTitle(search);
    }


    @Override
    public List<Todo> searchAll() {
        return repository.findAll();
    }

    @Override
    public void updateTodo(int id, String title) {
        Todo todo = repository.findById(id).orElseThrow(noTodoException());
        todo.setTitle(title);

    }

    @Override
    public void deleteTodo(int id) {
        Todo todo = repository.findById(id).orElseThrow(noTodoException());
        repository.remove(todo);
    }

    @Override
    public void toggleComplete(int id) {
        Todo todo = repository.findById(id).orElseThrow(noTodoException());
        todo.toggleComplete();
    }

    private static Supplier<IllegalArgumentException> noTodoException() {
        return () -> new IllegalArgumentException("해당 id에 해당하는 할 일이 존재하지 않습니다.");
    }
}
