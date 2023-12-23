package kr.ac.phdljr.boardrefactor.domain.comment.controller;

import java.util.List;
import kr.ac.phdljr.boardrefactor.domain.comment.dto.request.CommentCreateRequestDto;
import kr.ac.phdljr.boardrefactor.domain.comment.dto.request.CommentUpdateRequestDto;
import kr.ac.phdljr.boardrefactor.domain.comment.dto.response.CommentResponseDto;
import kr.ac.phdljr.boardrefactor.domain.comment.service.CommentService;
import kr.ac.phdljr.boardrefactor.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/comments/boards/{boardId}")
    public ResponseEntity<List<CommentResponseDto>> getCommentAll(
        @PageableDefault(size = 20, page = 0) Pageable pageable,
        @PathVariable Long boardId){
        List<CommentResponseDto> responseDto = commentService.getComments(boardId, pageable);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/comments/boards/{boardId}")
    public ResponseEntity<Void> createComment(
        @PathVariable Long boardId,
        @RequestBody CommentCreateRequestDto commentCreateRequestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails){
        commentService.createComment(boardId, commentCreateRequestDto, userDetails.getUser());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/comments/{commentId}")
    public ResponseEntity<Void> updateComment(
        @PathVariable Long commentId,
        @RequestBody CommentUpdateRequestDto commentUpdateRequestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails){
        commentService.updateComment(commentId, commentUpdateRequestDto, userDetails.getUser());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(
        @PathVariable Long commentId,
        @AuthenticationPrincipal UserDetailsImpl userDetails){
        commentService.deleteComment(commentId, userDetails.getUser());
        return ResponseEntity.ok().build();
    }
}
