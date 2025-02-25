package dev.twominutes.notetaking.controller.note;

import dev.twominutes.notetaking.dto.note.NoteDto;
import dev.twominutes.notetaking.models.Note;
import dev.twominutes.notetaking.security.service.CustomUserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/notes")
public interface noteController {
    @PostMapping("/create")
    ResponseEntity<NoteDto> createNote(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody NoteDto noteDto
    );

    @PostMapping("/update/{id}")
    ResponseEntity<NoteDto> updateNote(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long id,
            @RequestBody NoteDto noteDto
    );

    @GetMapping("/{id}")
    ResponseEntity<NoteDto> getNoteById(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long id
    );

    @GetMapping("/user/{userId}")
    ResponseEntity<List<NoteDto>> getNotesByUserId(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long userId
    );

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteNote(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long id);
}
