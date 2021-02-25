package com.headmostlab.notes.ui.note;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;

import com.headmostlab.notes.model.Note;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

public class NoteViewModelImpl extends androidx.lifecycle.ViewModel implements NoteViewModel {

    private static final String NOTE_KEY = "NOTE";

    private final SavedStateHandle dataStorage;
    private MutableLiveData<Note> note = new MutableLiveData<>();
    private MutableLiveData<Note> noteToShare = new MutableLiveData<>();

    public NoteViewModelImpl(SavedStateHandle savedState) {
        dataStorage = savedState;
        Note noteTmp = dataStorage.get(NOTE_KEY);
        if (noteTmp != null) {
            note.setValue(noteTmp);
        }
    }

    @Override
    public LiveData<Note> getSelectedNote() {
        return note;
    }

    @Override
    public LiveData<Note> getNoteToShare() {
        return noteToShare;
    }

    @Override
    public void setNote(Note note) {
        dataStorage.set(NOTE_KEY, note);
        this.note.setValue(note);
    }

    @Override
    public void share() {
        noteToShare.setValue(note.getValue());
    }

    @Override
    public void save(String title, String description, String date) {
        Note note = this.note.getValue();
        note.setTitle(title);
        note.setDescription(description);
        try {
            note.setCreationDate(DateFormat.getDateInstance().parse(date));
        } catch (ParseException ignore) {
        }
        this.note.setValue(note);
    }
//
//    @Override
//    public void save(Note newNote) {
//        Note note = this.note.getValue();
//        note.setTitle(newNote.getTitle());
//        note.setDescription(newNote.getDescription());
//        note.setCreationDate(newNote.getCreationDate());
//    }

}
