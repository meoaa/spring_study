package v2.service;

import domain.Todo;
import v2.repository.MemoryTodoRepositoryV2;
import v2.repository.TodoRepositoryV2;

import java.util.List;
import java.util.function.Supplier;

public class TodoServiceImplV2 implements TodoServiceV2{

    private final TodoRepositoryV2 repository = new MemoryTodoRepositoryV2();//DIP 위반

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
