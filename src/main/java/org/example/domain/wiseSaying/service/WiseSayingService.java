package org.example.domain.wiseSaying.service;

import org.example.domain.wiseSaying.WiseSaying;
import org.example.domain.wiseSaying.repository.WiseSayingRepository;

import java.util.List;

public class WiseSayingService {

    WiseSayingRepository wiseSayingRepository = new WiseSayingRepository();

    public WiseSaying writeService(String saying, String author) {
        WiseSaying wiseSaying = new WiseSaying(0, saying, author);
        wiseSaying = wiseSayingRepository.save(wiseSaying);

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

        wiseSayingRepository.save(wiseSaying);
    }
}
