package v3;

import domain.Todo;
import v3.repository.TodoRepositoryV3;
import v3.service.TodoServiceV3;

import java.util.List;
import java.util.Scanner;

public class TodoProgramV3 {

    public static void main(String[] args) {
        /**
         * 생성자 주입 방식을 사용하여 구체 클래스가 아닌 추상화(인터페이스)에 의존하도록 설계함.
         * 이를 통해 다른 Service나 Repository 구현체로 교체하더라도,
         * 생성자 주입만 변경하면 기존 코드를 수정하지 않고도 유연하게 대응할 수 있음.
         * → DIP(의존 역전 원칙)를 만족하는 구조.
         */
        TodoConfig config = new TodoConfig();
        final TodoRepositoryV3 repository = config.todoRepository();
        final TodoServiceV3 service = config.todoService(repository);


        Scanner sc = new Scanner(System.in);
        int menu;
        int id;
        String title;
        Todo todo;
        List<Todo> todos;

        while(true){
            System.out.println("=========================================================");
            System.out.println("Todo List");
            System.out.println("메뉴를 선택해주세요.");
            System.out.println("1.등록  2.조회  3.수정  4.삭제  5.상태변경 6.전체조회 7.종료");
            System.out.println("=========================================================");
            System.out.println();
            menu = sc.nextInt();sc.nextLine();

            if(menu == 7) {
                System.out.println("종료합니다.");
                break;
            }
            switch(menu){
                case 1 :
                    System.out.println("할 일을 등록합니다.");
                    System.out.print("할 일을 입력해주세요. : ");

                    title = sc.nextLine();
                    service.addTodo(new Todo(title));

                    System.out.println("할 일이 등록되었습니다.");
                    break;

                case 2 :
                    System.out.println("할 일을 조회합니다.");
                    System.out.println("id 또는 할 일을 입력해주세요.");

                    final String search = sc.nextLine();
                    if(checkNumber(search)){
                        id = Integer.parseInt(search);
                        try{
                            todo = service.searchById(id);
                            System.out.println(todo);
                        }catch(IllegalArgumentException e){
                            System.out.println(e.getMessage());
                        }
                    }else{
                        todos = service.searchByTitle(search);
                        if(todos.isEmpty()){
                            System.out.println("일치하는 할 일이 없습니다.");
                        }else{
                            todos.forEach(System.out::println);
                        }
                    }

                    break;
                case 3 :
                    System.out.println("할 일을 수정합니다.");
                    System.out.println("수정할 할 일의 id를 입력해주세요.");
                    id = sc.nextInt();
                    title = sc.nextLine();
                    try{
                        service.updateTodo(id, title);
                        System.out.println("할 일이 변경되었습니다.");
                    }catch (IllegalArgumentException e){
                        System.out.println(e.getMessage());
                    }

                    break;
                case 4 :
                    System.out.println("할 일을 삭제합니다.");
                    System.out.println("삭제할 할 일의 id를 입력해주세요.");
                    id = sc.nextInt();sc.nextLine();
                    try{
                        service.deleteTodo(id);
                    }catch (IllegalArgumentException e){
                        System.out.println(e.getMessage());
                    }

                    break;
                case 5 :
                    System.out.println("할 일의 상태를 변경합니다.");
                    System.out.println("변경할 할 일의 id를 입력해주세요.");
                    id = sc.nextInt();sc.nextLine();

                    try{
                        service.toggleComplete(id);
                        System.out.println("할 일의 상태가 변경되었습니다.");
                    }catch (IllegalArgumentException e){
                        System.out.println(e.getMessage());
                    }

                    break;

                case 6 :
                    System.out.println("할 일들의 전체를 조회합니다.");
                    todos = service.searchAll();

                    if(todos.isEmpty()){
                        System.out.println("할 일이 존재하지 않습니다.");
                    }else{
                        todos.forEach(System.out::println);
                    }

                    break;

                default: System.out.println("잘못된 번호를 입력했습니다.");
            }
        }
    }

    private static boolean checkNumber(String search) {
        if(search == null || search.isEmpty()) return false;
        try{
            Integer.parseInt(search);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }

}
