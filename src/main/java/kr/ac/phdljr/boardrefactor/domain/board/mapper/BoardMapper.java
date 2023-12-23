package kr.ac.phdljr.boardrefactor.domain.board.mapper;

import java.util.List;
import kr.ac.phdljr.boardrefactor.domain.board.dto.response.BoardAllGetResponseDto;
import kr.ac.phdljr.boardrefactor.domain.board.dto.response.BoardGetResponseDto;
import kr.ac.phdljr.boardrefactor.domain.board.entity.Board;
import kr.ac.phdljr.boardrefactor.domain.board.dto.request.BoardCreateRequestDto;
import kr.ac.phdljr.boardrefactor.domain.comment.dto.response.CommentResponseDto;
import kr.ac.phdljr.boardrefactor.domain.comment.entity.Comment;
import kr.ac.phdljr.boardrefactor.domain.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BoardMapper {

    BoardMapper INSTANCE = Mappers.getMapper(BoardMapper.class);

    Board toBoard(BoardCreateRequestDto boardCreateRequestDto, User user);

    @Mapping(source = "user.nickname", target = "nickname")
    CommentResponseDto toCommentResponseDto(Comment comment);

    @Mapping(source = "board.user.nickname", target = "nickname")
    BoardGetResponseDto toBoardResponseDto(Board board, List<Comment> comments);

    @Mapping(source = "user.nickname", target = "nickname")
    BoardAllGetResponseDto toBoardAllGetResponseDto(Board board);

    List<BoardAllGetResponseDto> toBoardAllGetResponseDtoList(List<Board> boards);
}
