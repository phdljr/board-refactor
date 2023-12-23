package kr.ac.phdljr.boardrefactor.domain.comment.mapper;

import java.util.List;
import kr.ac.phdljr.boardrefactor.domain.board.entity.Board;
import kr.ac.phdljr.boardrefactor.domain.comment.dto.request.CommentCreateRequestDto;
import kr.ac.phdljr.boardrefactor.domain.comment.dto.response.CommentResponseDto;
import kr.ac.phdljr.boardrefactor.domain.comment.entity.Comment;
import kr.ac.phdljr.boardrefactor.domain.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentMapper {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    @Mapping(source = "board", target = "board")
    @Mapping(source = "commentCreateRequestDto.content", target = "content")
    Comment toComment(CommentCreateRequestDto commentCreateRequestDto, User user, Board board);

    @Mapping(source = "user.nickname", target = "nickname")
    List<CommentResponseDto> toCommentResponseDtoList(List<Comment> comments);
}
