package org.example.domain.wiseSaying.controller;

import org.example.domain.wiseSaying.Rq;
import org.example.domain.wiseSaying.WiseSaying;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WiseSayingController {

    private Scanner sc;

    public WiseSayingController(Scanner sc) {
        this.sc = sc;
    }

    //명언 배열
    private List<WiseSaying> wiseSayings = new ArrayList<>();
    //명언 번호
    private int number = -1;

    public void registration() {
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

    public void list() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");

        //마지막 번호의 명언부터 첫 번호의 명언까지 출력
        for (int i = wiseSayings.size() - 1; i >= 0; i--) {
            System.out.println(wiseSayings.get(i).getId() + " / " +wiseSayings.get(i).getAuthor() + " / " + wiseSayings.get(i).getContent());
        }
    }

    public void delete(Rq rq) {
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

    public void edit(Rq rq) {
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
