package dev.twominutes.notetaking.service.note;

import dev.twominutes.notetaking.models.Note;

import java.util.List;
import java.util.Optional;

public interface NoteService {
    Note save (Note note);
    Optional<Note> findById(Long id);
    List<Note> findByUserId(Long userId);
    void update(Note note);
    void deleteById(Long id);
}
