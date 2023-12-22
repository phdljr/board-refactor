package kr.ac.phdljr.boardrefactor.domain.board.mapper;

import kr.ac.phdljr.boardrefactor.domain.board.dto.response.BoardResponseDto;
import kr.ac.phdljr.boardrefactor.domain.board.entity.Board;
import kr.ac.phdljr.boardrefactor.domain.board.dto.request.BoardCreateRequestDto;
import kr.ac.phdljr.boardrefactor.domain.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BoardMapper {

    BoardMapper INSTANCE = Mappers.getMapper(BoardMapper.class);

    Board toBoard(BoardCreateRequestDto boardCreateRequestDto, User user);

    @Mapping(source = "user.nickname", target = "nickname")
    BoardResponseDto toBoardResponseDto(Board board);
}
