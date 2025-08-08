package org.example.domain.wiseSaying.repository;

import org.example.domain.wiseSaying.WiseSaying;

import java.util.ArrayList;
import java.util.List;

public class WiseSayingRepository {

    //명언 번호
    private int number = -1;
    //명언 배열
    private List<WiseSaying> wiseSayings = new ArrayList<>();

    public boolean deleteService(int id) {
        return wiseSayings.removeIf(wiseSaying -> wiseSaying.getId() == id);
    }

    public WiseSaying findbyIDOrNull(int id) {
        return wiseSayings.stream()
                .filter(wiseSaying -> wiseSaying.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public WiseSaying save(WiseSaying wiseSaying) {

        if (wiseSaying.isNew()) {
            //새로운 명언인 경우
            number++;
            wiseSaying.setId(number + 1);
            wiseSayings.add(wiseSaying);
        }

        return wiseSaying;

    }

    public List<WiseSaying> reversedwiseSayings () {
        return wiseSayings.reversed();
    }

}
