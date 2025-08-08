package org.example.domain.wiseSaying;

import org.example.domain.wiseSaying.controller.SystemController;
import org.example.domain.wiseSaying.controller.WiseSayingController;

import java.util.Scanner;

public class App {
    //명령 입력받을 문자열
    private Scanner sc = new Scanner(System.in);

    private SystemController systemController = new SystemController();
    private WiseSayingController wiseSayingController = new WiseSayingController(sc);

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
            if (actionName.equals("등록")) wiseSayingController.write();

            //"목록"을 입력받았을 경우
            if (actionName.equals("목록")) wiseSayingController.list();

            //삭제 명령을 입력받았을 경우. 명언 id가 다르게 들어오기 때문에 정규표현식으로 검사
            if (actionName.equals("삭제")) {
                wiseSayingController.delete(rq);
            }
            //수정 명령을 입력받았을 경우. 역시 정규표현식으로 검사
            if (actionName.equals("수정")) {
                wiseSayingController.edit(rq);
            }
            if (actionName.equals("종료")) {
                systemController.exit();
                break;
            }
        }
    }
}
