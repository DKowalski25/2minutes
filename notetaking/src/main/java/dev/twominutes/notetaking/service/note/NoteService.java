package dev.twominutes.notetaking.service.note;

import dev.twominutes.notetaking.dto.note.NoteDto;

import java.util.List;
import java.util.Optional;

public interface NoteService {
    NoteDto save(NoteDto noteDto);
    Optional<NoteDto> findById(Long id);
    List<NoteDto> findByUserId(Long userId);
    void update(NoteDto noteDto);
    void deleteById(Long id);
}
