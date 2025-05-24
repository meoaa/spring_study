package domain;

import java.time.LocalDateTime;

public class Todo {
    public static int sequence;
    private int id;
    private String title;
    private boolean completed;
    private LocalDateTime createAt;
    private LocalDateTime updatedAt;

    public Todo(String title) {
        this.id = ++sequence;
        this.title = title;
        this.completed = false;
        this.createAt = LocalDateTime.now();
    }

    public void toggleComplete(){
        this.completed = !completed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }


    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                ", createAt=" + createAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
