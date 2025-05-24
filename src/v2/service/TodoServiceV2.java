package v2.service;

import domain.Todo;

import java.util.List;
import java.util.Optional;

public interface TodoServiceV2 {

    void addTodo(Todo todo);

    Todo searchById(int id);

    List<Todo> searchByTitle(String search);

    List<Todo> searchAll();

    void updateTodo(int id, String title);

    void deleteTodo(int id);

    void toggleComplete(int id);
}
