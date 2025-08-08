package org.example.domain.wiseSaying;

import org.example.domain.wiseSaying.controller.SystemController;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    //명령 입력받을 문자열
    private Scanner sc = new Scanner(System.in);
    //명언 배열
    private List <WiseSaying> wiseSayings = new ArrayList<>();
    //명언 번호
    private int number = -1;
    private SystemController systemController = new SystemController();

    public void run() {

        System.out.println("== 명언 앱 ==");
        //"종료" 명령이 입력될 때까지 반복
        String command = "";

        while (true) {
            System.out.print("명령) ");
            //명령 입력 받기
            command = sc.nextLine();

            Rq rq = new Rq(command);
            String actionName = rq.getActionName();

            //"등록"을 입력받았을 경우
            if (actionName.equals("등록")) registration();

            //"목록"을 입력받았을 경우
            if (actionName.equals("목록")) list();

            //삭제 명령을 입력받았을 경우. 명언 id가 다르게 들어오기 때문에 정규표현식으로 검사
            if (actionName.equals("삭제")) {
                delete(rq);
            }
            //수정 명령을 입력받았을 경우. 역시 정규표현식으로 검사
            if (actionName.equals("수정")) {
                edit(rq);
            }
            if (actionName.equals("종료")) {
                systemController.exit();
                break;
            }
        }
    }

    private void registration() {
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

//    private int findIndexById(int id) {
//
//        return IntStream.range(0, wiseSayings.size())
//                .filter(i -> wiseSayings.get(i).getId() == id)
//                .findFirst()
//                .orElse(-1);
//
//    }

    private void delete(Rq rq) {
        //입력값에서 id숫자 추출
        int id = rq.getParamAsInt("id", -1);

        if (wiseSayings.removeIf(wiseSaying -> wiseSaying.getId() == id)) {
            System.out.println((id) + "번 명언이 삭제되었습니다.");
        } else {
            System.out.println((id) + "번 명언은 존재하지 않습니다.");
        }
    }

    private WiseSaying findbyIDOrNull(int id) {
        return wiseSayings.stream()
                .filter(wiseSaying -> wiseSaying.getId() == id)
                .findFirst()
                .orElse(null);
    }

    private void edit(Rq rq) {
        int id = rq.getParamAsInt("id", -1);

                WiseSaying wiseSaying = findbyIDOrNull(id);

        //삭제 동작과 같은 예외 처리
        if (wiseSaying == null) {
            System.out.println(id + "번 명언은 존재하지 않습니다.");
            return;
        }

        System.out.println("명언(기존) : " + wiseSaying.getContent());
        System.out.print("명언 : ");
        wiseSaying.setContent(sc.nextLine());
        System.out.println("작가(기존) : " + wiseSaying.getAuthor());
        System.out.print("작가 : ");
        wiseSaying.setAuthor(sc.nextLine());
    }
}
