package dev.twominutes.notetaking.service.note;

import dev.twominutes.notetaking.models.Note;
import dev.twominutes.notetaking.repository.note.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService{

    private final NoteRepository noteRepository;

    @Override
    public Note save(Note note) {
        return noteRepository.save(note);
    }

    @Override
    public Optional<Note> findById(Long id) {
        return noteRepository.findById(id);
    }

    @Override
    public List<Note> findByUserId(Long userId) {
        return noteRepository.findByUserId(userId);
    }

    @Override
    public void update(Note note) {
        noteRepository.update(note);
    }

    @Override
    public void deleteById(Long id) {
        noteRepository.deleteById(id);
    }
}
