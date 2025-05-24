package v3;

import v3.repository.MemoryTodoRepositoryV3;
import v3.repository.TodoRepositoryV3;
import v3.service.TodoServiceImplV3;
import v3.service.TodoServiceV3;

public class TodoConfig {

    /**
     * 각 구현체는 생성자 주입을 통해 외부에서 필요한 의존성을 전달받으며,
     * 자신이 맡은 역할(비즈니스 로직, 데이터 저장 등)에만 집중할 수 있도록 구성함.
     * 이 구조는 의존 역전 원칙(DIP)을 만족하며, 구현체 교체 시에도 조립 코드만 수정하면 됨.
     */
    public TodoServiceV3 todoService(TodoRepositoryV3 todoRepositoryV3){
        return new TodoServiceImplV3(todoRepositoryV3);
    }

    public TodoRepositoryV3 todoRepository(){
        return new MemoryTodoRepositoryV3();
    }

}
