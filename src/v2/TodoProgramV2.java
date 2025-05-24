package v2;

import domain.Todo;
import v2.service.TodoServiceImplV2;
import v2.service.TodoServiceV2;

import java.util.List;
import java.util.Scanner;

public class TodoProgramV2 {
    public static void main(String[] args) {
        //service와 repository를 분리하면서 관심사를 분리함
        //각각의 객체는 자신의 역할만 수행함
        //TodoProgramV2 객체는 컨트롤러 역할을 수행하며 입력과 출력 그리고 입력값에 대한 검증만
        //service 단에서는 입력받은 id값에 대한 검증을 수행함
        //repository 단에서는 예외검증이나 다른 로직이 없이 철저하게 데이터의 저장 / 조회만 수행함
        //단일 책임 원칙(SRP)원칙은 준수하였으나 의존 역전 원칙(DIP)은 지켜지지 않음
        //추상화 즉 인터페이스에 의존하여야 하나 service단과 controller단에서 직접 객체를 생성하고 있음
        //DIP를 위반할 경우 유지보수와 재사용성이 떨어진다.

        TodoServiceV2 service = new TodoServiceImplV2();//DIP 위반

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
