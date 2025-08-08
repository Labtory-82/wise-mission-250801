package org.example.domain.wiseSaying.controller;

import org.example.domain.wiseSaying.Rq;
import org.example.domain.wiseSaying.WiseSaying;
import org.example.domain.wiseSaying.service.WiseSayingService;

import java.util.List;
import java.util.Scanner;

public class WiseSayingController {

    private Scanner sc;
    WiseSayingService wiseSayingService = new WiseSayingService();

    public WiseSayingController(Scanner sc) {
        this.sc = sc;
    }

    public void write() {
        System.out.print("명언 : ");
        String saying = sc.nextLine();
        System.out.print("작가 : ");
        String author = sc.nextLine();

        WiseSaying wiseSaying = wiseSayingService.writeService(saying, author);
        System.out.println(wiseSaying.getId() + "번 명언이 등록되었습니다.");

    }


    public void list() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");

        List<WiseSaying> wiseSayings = wiseSayingService.reversedwiseSayings();
        //마지막 번호의 명언부터 첫 번호의 명언까지 출력
        for (WiseSaying wiseSaying: wiseSayings) {
            System.out.println(wiseSaying.getId() + " / " + wiseSaying.getAuthor() + " / " + wiseSaying.getSaying());
        }
    }

    public void delete(Rq rq) {
        //입력값에서 id숫자 추출
        int id = rq.getParamAsInt("id", -1);

        if (wiseSayingService.deleteService(id)) {
            System.out.println((id) + "번 명언이 삭제되었습니다.");
        } else {
            System.out.println((id) + "번 명언은 존재하지 않습니다.");
        }
    }

    public void edit(Rq rq) {
        int id = rq.getParamAsInt("id", -1);

        WiseSaying wiseSaying = wiseSayingService.findbyIDOrNull(id);

        //삭제 동작과 같은 예외 처리
        if (wiseSaying == null) {
            System.out.println(id + "번 명언은 존재하지 않습니다.");
            return;
        }

        System.out.println("명언(기존) : " + wiseSaying.getSaying());
        System.out.print("명언 : ");
        String saying = sc.nextLine();
        System.out.println("작가(기존) : " + wiseSaying.getAuthor());
        System.out.print("작가 : ");
        String author = sc.nextLine();

        wiseSayingService.editService(wiseSaying, saying, author);
    }

}
