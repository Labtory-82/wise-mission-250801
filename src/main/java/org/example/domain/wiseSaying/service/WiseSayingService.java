package org.example.domain.wiseSaying.service;

import org.example.domain.wiseSaying.WiseSaying;
import org.example.domain.wiseSaying.repository.WiseSayingRepository;

import java.util.List;

public class WiseSayingService {

    //명언 번호
    private int number = -1;
    WiseSayingRepository wiseSayingRepository = new WiseSayingRepository();

    public WiseSaying writeService(String saying, String author) {
        //명언 번호. 등록할 때마다 증가
        number++;
        WiseSaying wiseSaying = new WiseSaying(number + 1, saying, author);
        wiseSayingRepository.save(wiseSaying);

        return wiseSaying;
    }

    public List<WiseSaying> reversedwiseSayings () {
        return wiseSayingRepository.reversedwiseSayings();
    }

    public boolean deleteService(int id) {
        return wiseSayingRepository.deleteService(id);
    }

    public WiseSaying findbyIDOrNull(int id) {
        return wiseSayingRepository.findbyIDOrNull(id);
    }

    public void editService(WiseSaying wiseSaying, String saying, String author) {
        wiseSaying.setSaying(saying);
        wiseSaying.setAuthor(author);
    }
}
