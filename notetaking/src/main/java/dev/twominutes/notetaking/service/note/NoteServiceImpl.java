package dev.twominutes.notetaking.service.note;

import dev.twominutes.notetaking.dto.note.NoteDto;
import dev.twominutes.notetaking.dto.note.NoteMapper;
import dev.twominutes.notetaking.models.Note;
import dev.twominutes.notetaking.repository.note.NoteRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService{

    private final NoteRepository noteRepository;
    private final NoteMapper noteMapper;

    @Override
    public NoteDto save(NoteDto noteDto) {
        Note note = noteMapper.toEntity(noteDto);
        note = noteRepository.save(note);
        return noteMapper.toDto(note);
    }

    @Override
    public Optional<NoteDto> findById(Long id) {
        Optional<Note> note = noteRepository.findById(id);
        return note.map(noteMapper::toDto);
    }

    @Override
    public List<NoteDto> findByUserId(Long userId) {
        List<Note> notes = noteRepository.findByUserId(userId);
        return notes.stream()
                .map(noteMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void update(NoteDto noteDto) {
        Note note = noteMapper.toEntity(noteDto);
        noteRepository.update(note);
    }

    @Override
    public void deleteById(Long id) {
        noteRepository.deleteById(id);
    }
}
