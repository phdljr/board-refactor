package kr.ac.phdljr.boardrefactor.domain.board.controller;

import jakarta.validation.Valid;
import java.util.List;
import kr.ac.phdljr.boardrefactor.domain.board.dto.request.BoardCreateRequestDto;
import kr.ac.phdljr.boardrefactor.domain.board.dto.request.BoardUpdateRequestDto;
import kr.ac.phdljr.boardrefactor.domain.board.dto.response.BoardAllGetResponseDto;
import kr.ac.phdljr.boardrefactor.domain.board.dto.response.BoardGetResponseDto;
import kr.ac.phdljr.boardrefactor.domain.board.service.BoardService;
import kr.ac.phdljr.boardrefactor.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
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
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/boards")
    public ResponseEntity<Void> createBoard(
        @Valid @RequestBody BoardCreateRequestDto boardCreateRequestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        boardService.createBoard(boardCreateRequestDto, userDetails.getUser());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/boards/{boardId}")
    public ResponseEntity<BoardGetResponseDto> getBoard(
        @PathVariable Long boardId) {
        BoardGetResponseDto responseDto = boardService.getBoard(boardId);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/boards")
    public ResponseEntity<List<BoardAllGetResponseDto>> getBoardAll(
        @PageableDefault(size = 20, page = 0) Pageable pageable) {
        List<BoardAllGetResponseDto> responseDto = boardService.getBoardAll(pageable);
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/boards/{boardId}")
    public ResponseEntity<Void> updateBoard(
        @PathVariable Long boardId,
        @Valid @RequestBody BoardUpdateRequestDto boardUpdateRequestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        boardService.updateBoard(boardId, boardUpdateRequestDto, userDetails.getUser());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/boards/{boardId}")
    public ResponseEntity<Void> deleteBoard(
        @PathVariable Long boardId,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        boardService.deleteBoard(boardId, userDetails.getUser());
        return ResponseEntity.ok().build();
    }
}
