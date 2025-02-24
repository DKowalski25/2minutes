package dev.twominutes.notetaking.repository.note;

import dev.twominutes.notetaking.models.Note;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepository {
    Note save(Note note);
    Optional<Note> findById(Long id);
    List<Note> findByUserId(Long userId);
    void update(Note note);
    void deleteById(Long id);
}
