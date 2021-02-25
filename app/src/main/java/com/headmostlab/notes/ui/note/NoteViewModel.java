package com.headmostlab.notes.ui.note;

import androidx.lifecycle.LiveData;

import com.headmostlab.notes.model.Note;

import java.util.Date;

public interface NoteViewModel {

    LiveData<Note> getSelectedNote();

    LiveData<Note> getNoteToShare();

    void setNote(Note note);

    void setCreateDate(Date selection);

    void share();
}
