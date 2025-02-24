package dev.twominutes.notetaking.dto.note;

import dev.twominutes.notetaking.models.Note;
import org.springframework.stereotype.Component;

@Component
public class NoteMapper {

    public NoteDto toDto(Note note) {
        return NoteDto.builder()
                .id(note.getId())
                .userId(note.getUserId())
                .title(note.getTitle())
                .content(note.getContent())
                .createdAt(note.getCreateAt())
                .updatedAt(note.getUpdateAt())
                .build();
    }

    public Note toEntity(NoteDto noteDto) {
        return Note.builder()
                .id(noteDto.getId())
                .userId(noteDto.getUserId())
                .title(noteDto.getTitle())
                .content(noteDto.getContent())
                .createAt(noteDto.getCreatedAt())
                .updateAt(noteDto.getUpdatedAt())
                .build();
    }
}
