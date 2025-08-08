package org.example.domain.wiseSaying.service;

import org.example.domain.wiseSaying.WiseSaying;

import java.util.ArrayList;
import java.util.List;

public class WiseSayingService {

    //명언 배열
    private List<WiseSaying> wiseSayings = new ArrayList<>();
    //명언 번호
    private int number = -1;

    public WiseSaying writeService(String saying, String author) {
        //명언 번호. 등록할 때마다 증가
        number++;
        WiseSaying wiseSaying = new WiseSaying(number + 1, saying, author);
        wiseSayings.add(wiseSaying);

        return wiseSaying;
    }

    public List<WiseSaying> reversedwiseSayings () {
        return wiseSayings.reversed();
    }

    public boolean deleteService(int id) {
        return wiseSayings.removeIf(wiseSaying -> wiseSaying.getId() == id);
    }

    public WiseSaying findbyIDOrNull(int id) {
        return wiseSayings.stream()
                .filter(wiseSaying -> wiseSaying.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void editService(WiseSaying wiseSaying, String saying, String author) {
        wiseSaying.setSaying(saying);
        wiseSaying.setAuthor(author);
    }
}
