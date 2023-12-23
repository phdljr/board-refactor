package kr.ac.phdljr.boardrefactor.global.page;

import java.util.Objects;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public record PageRequestDto(
    Integer page,
    Integer size,
    String sortBy,
    String direction
) {

    public PageRequestDto {
        if (Objects.isNull(page)) {
            page = 1;
        }
        if (Objects.isNull(size)) {
            size = 20;
        }
        if (Objects.isNull(sortBy)){
            sortBy = "id";
        }
        if(Objects.isNull(direction)){
            direction = "desc";
        }
    }

    public Pageable toPageable() {
        Sort sort = Sort.by(sortBy);
        if (direction.equals("desc")) {
            sort.descending();
        } else {
            sort.ascending();
        }

        return PageRequest.of(page, size, sort);
    }
}
