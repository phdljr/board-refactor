package kr.ac.phdljr.boardrefactor.domain.boardlike.controller;

import kr.ac.phdljr.boardrefactor.domain.boardlike.service.BoardLikeService;
import kr.ac.phdljr.boardrefactor.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class BoardLikeController {

    private final BoardLikeService boardLikeService;

    @PostMapping("/likes/boards/{boardId}")
    public ResponseEntity<Void> createBoardLike(
        @PathVariable Long boardId,
        @AuthenticationPrincipal UserDetailsImpl userDetails){
        boardLikeService.createBoardLike(boardId, userDetails.getUser());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/likes/boards/{boardId}")
    public ResponseEntity<Void> deleteBoardLike(
        @PathVariable Long boardId,
        @AuthenticationPrincipal UserDetailsImpl userDetails){
        boardLikeService.deleteBoardLike(boardId, userDetails.getUser());
        return ResponseEntity.ok().build();
    }
}
