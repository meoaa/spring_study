import domain.Todo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<Todo> store = new ArrayList<>();
        int menu;
        int id;
        Optional<Todo> findTodo;


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
                    String title = sc.nextLine();
                    store.add(new Todo(title));
                    System.out.println("할 일이 등록되었습니다.");
                    break;

                case 2 :
                    System.out.println("할 일을 조회합니다.");
                    System.out.println("id 또는 할 일을 입력해주세요.");
                    final String search = sc.nextLine();
                    boolean checkNumber = checkNumber(search);
                    if(checkNumber){
                        final int searchNum = Integer.parseInt(search);
                        List<Todo> result = store.stream().filter(elem -> elem.getId() == searchNum)
                                .toList();

                        checkTodo(result);
                    }else{
                        List<Todo> result = store.stream()
                                .filter(elem -> elem.getTitle().contains(search))
                                .toList();

                        checkTodo(result);
                    }
                    break;
                case 3 :
                    System.out.println("할 일을 수정합니다.");
                    System.out.println("수정할 할 일의 id를 입력해주세요.");
                    id = sc.nextInt();sc.nextLine();

                    findTodo = getTodo(store, id);
                    if(findTodo.isEmpty()){
                        System.out.println("해당 id에 해당하는 할 일이 존재하지 않습니다.");
                    }else{
                        System.out.print("변경할 내용을 입력해주세요. : ");
                        String updateTitle = sc.nextLine();
                        Todo todo = findTodo.get();
                        todo.setTitle(updateTitle);
                        todo.setUpdatedAt(LocalDateTime.now());
                        System.out.println("할 일이 변경되었습니다.");
                    }
                    break;
                case 4 :
                    System.out.println("할 일을 삭제합니다.");
                    System.out.println("삭제할 할 일의 id를 입력해주세요.");
                    id = sc.nextInt();sc.nextLine();

                    findTodo = getTodo(store, id);
                    if(findTodo.isEmpty()){
                        System.out.println("해당 id에 해당하는 할 일이 존재하지 않습니다.");
                    }else{
                        System.out.print("정말로 삭제하시겠습니까? (Y/N)");
                        String checkDelete = sc.nextLine();
                        if(checkDelete.equalsIgnoreCase("Y")){
                            store.remove(findTodo.get());
                            System.out.println("삭제되었습니다.");
                        }
                    }
                    break;
                case 5 :
                    System.out.println("할 일의 상태를 변경합니다.");
                    System.out.println("변경할 할 일의 id를 입력해주세요.");
                    id = sc.nextInt();sc.nextLine();
                    findTodo = getTodo(store , id);
                    if(findTodo.isEmpty()){
                        System.out.println("해당 id에 해당하는 할 일이 존재하지 않습니다.");
                    }else{
                        Todo todo  = findTodo.get();
                        todo.toggleComplete();
                        todo.setUpdatedAt(LocalDateTime.now());
                        System.out.println("상태가 변경되었습니다.");
                    }
                    break;
                case 6 :
                    System.out.println("할 일들의 전체를 조회합니다.");
                    if(store.isEmpty()){
                        System.out.println("할 일이 존재하지 않습니다.");
                    }else{
                        store.forEach(System.out::println);
                    }
                    break;
                default: System.out.println("잘못된 번호를 입력했습니다.");
            }
        }
    }

    private static Optional<Todo> getTodo(ArrayList<Todo> store, int finalId) {
        Optional<Todo> findTodo = store.stream().filter(elem -> elem.getId() == finalId).findFirst();
        return findTodo;
    }

    private static void checkTodo(List<Todo> result) {
        if(result.isEmpty()){
            System.out.println("조회 결과가 없습니다.");
        }else{
            result.forEach(System.out::println);
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