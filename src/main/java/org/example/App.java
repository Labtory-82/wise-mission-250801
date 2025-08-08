package org.example;

import java.util.Scanner;

public class App {
    Scanner sc = new Scanner(System.in);
    //명령 입력받을 문자열
    String command = "";

    //명언 배열
    WiseSaying[] wiseSayings = new WiseSaying[999];
    //명언 번호
    int number = -1;

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

    public void registration() {
        //명언 번호. 등록할 때마다 증가
        number++;

        WiseSaying wiseSaying = new WiseSaying();
        wiseSaying.id = number + 1;
        System.out.print("명언 : ");
        wiseSaying.content = sc.nextLine();
        System.out.print("작가 : ");
        wiseSaying.author = sc.nextLine();
        wiseSayings[number] = wiseSaying;

        System.out.println((number + 1) + "번 명언이 등록되었습니다.");

    }

    public void list() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");

        //마지막 번호의 명언부터 첫 번호의 명언까지 출력
        for (int i = number; i >= 0; i--) {
            if (wiseSayings[i] == null) continue;
            System.out.println(wiseSayings[i].id + " / " + wiseSayings[i].author + " / " + wiseSayings[i].content);
        }
    }

    public void delete() {
        //입력값에서 id숫자 추출
        int id = Integer.parseInt(command.substring(6)) - 1;

        //명언 최대 개수를 초과한 숫자를 입력받았을 경우 또는 존재하지 않는 명언일 경우 예외 처리
        if (id > 999 || (wiseSayings[id] == null)) {
            System.out.println((id + 1) + "번 명언은 존재하지 않습니다.");
        }
        //예외가 발생하지 않는다면 정상적으로 삭제 처리
        else {
            wiseSayings[id] = null;
            System.out.println((id + 1) + "번 명언이 삭제되었습니다.");
        }
    }

    public void edit() {
        int id = Integer.parseInt(command.substring(6)) - 1;

        //삭제 동작과 같은 예외 처리
        if (id > 999 || wiseSayings[id] == null) {
            System.out.println((id + 1) + "번 명언은 존재하지 않습니다.");
        }
        //예외가 발생하지 않는다면 정상적으로 수정 처리
        else {
            System.out.println("명언(기존) : " + wiseSayings[id].content);
            System.out.print("명언 : ");
            wiseSayings[id].content = sc.nextLine();
            System.out.println("작가(기존) : " + wiseSayings[id].author);
            System.out.print("작가 : ");
            wiseSayings[id].author = sc.nextLine();
        }
    }
}
