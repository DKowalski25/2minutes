package dev.twominutes.notetaking.controller.note;

import dev.twominutes.notetaking.dto.note.NoteDto;
import dev.twominutes.notetaking.security.service.CustomUserDetails;
import dev.twominutes.notetaking.service.note.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notes")
@RequiredArgsConstructor
public class noteControllerImpl implements noteController {
    private final NoteService noteService;


    @Override
    public ResponseEntity<NoteDto> createNote(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody NoteDto noteDto
    ) {
        NoteDto savedNoteDto = noteService.save(noteDto);
        return ResponseEntity.ok(savedNoteDto);
    }

    @Override
    public ResponseEntity<NoteDto> updateNote(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long id,
            @RequestBody NoteDto noteDto
    ) {
        noteDto.setId(id);
        noteService.update(noteDto);
        return ResponseEntity.ok(noteDto);
    }

    @Override
    public ResponseEntity<NoteDto> getNoteById(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long id
    ) {
        return noteService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<List<NoteDto>> getNotesByUserId(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long userId
    ) {
        List<NoteDto> notes = noteService.findByUserId(userId);
        return ResponseEntity.ok(notes);
    }

    @Override
    public ResponseEntity<Void> deleteNote(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long id
    ) {
        noteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
