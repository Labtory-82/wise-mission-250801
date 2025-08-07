package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private Scanner sc = new Scanner(System.in);
    //명령 입력받을 문자열
    private String command = "";

    //명언 배열
    private List <WiseSaying> wiseSayings = new ArrayList<>();
    //명언 번호
    private int number = -1;

    public void run() {

        System.out.println("== 명언 앱 ==");
        //"종료" 명령이 입력될 때까지 반복
        while (!command.equals("종료")) {
            System.out.print("명령) ");
            //명령 입력 받기
            command = sc.nextLine();

            //"등록"을 입력받았을 경우
            if (command.equals("등록")) registration();

            //"목록"을 입력받았을 경우
            if (command.equals("목록")) list();

            //삭제 명령을 입력받았을 경우. 명언 id가 다르게 들어오기 때문에 정규표현식으로 검사
            if (command.matches("^삭제\\?id=\\d+$")) delete();

            //수정 명령을 입력받았을 경우. 역시 정규표현식으로 검사
            if (command.matches("^수정\\?id=\\d+$")) edit();
        }
    }

    private  void registration() {
        //명언 번호. 등록할 때마다 증가
        number++;

        System.out.print("명언 : ");
        String saying = sc.nextLine();
        System.out.print("작가 : ");
        String author = sc.nextLine();
        WiseSaying wiseSaying = new WiseSaying(number + 1, saying, author);
        wiseSayings.add(wiseSaying);

        System.out.println((number + 1) + "번 명언이 등록되었습니다.");

    }

    private void list() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");

        //마지막 번호의 명언부터 첫 번호의 명언까지 출력
        for (int i = wiseSayings.size() - 1; i >= 0; i--) {
            System.out.println(wiseSayings.get(i).getId() + " / " +wiseSayings.get(i).getAuthor() + " / " + wiseSayings.get(i).getContent());
        }
    }

    private int findIndexById(int id) {
        //명언 id로 명언을 찾는 메소드
        for (int i = 0; i < wiseSayings.size(); i++) {
            if (wiseSayings.get(i).getId() == id) {
                return i;
            }
        }
        return -1; //명언이 없을 경우 -1 반환
    }

    private  void delete() {
        //입력값에서 id숫자 추출
        int id = Integer.parseInt(command.substring(6));

        int deleteTargetIndex = findIndexById(id);
        //명언 id로 명언을 찾지 못했을 경우 예외 처리
        if (deleteTargetIndex == -1) {
            System.out.println((id) + "번 명언은 존재하지 않습니다.");
        } else {
            wiseSayings.remove(deleteTargetIndex);
            System.out.println((id) + "번 명언이 삭제되었습니다.");
        }
    }

    private  void edit() {
        int id = Integer.parseInt(command.substring(6));

        int editTargetIndex = findIndexById(id);

        //삭제 동작과 같은 예외 처리
        if (editTargetIndex == -1) {
            System.out.println((id) + "번 명언은 존재하지 않습니다.");
        } else {
            WiseSaying wiseSaying = wiseSayings.get(editTargetIndex);
            System.out.println("명언(기존) : " + wiseSaying.getContent());
            System.out.print("명언 : ");
            wiseSaying.setContent(sc.nextLine());
            System.out.println("작가(기존) : " + wiseSaying.getAuthor());
            System.out.print("작가 : ");
            wiseSaying.setAuthor(sc.nextLine());
        }
    }
}
