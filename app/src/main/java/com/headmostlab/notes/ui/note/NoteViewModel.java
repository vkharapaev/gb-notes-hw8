package com.headmostlab.notes.ui.note;

import androidx.lifecycle.LiveData;

import com.headmostlab.notes.model.Note;

public interface NoteViewModel {

    LiveData<Note> getSelectedNote();

    LiveData<Note> getNoteToShare();

    void setNote(Note note);

    void share();

    void save(String title, String description, String date);
}
