package com.headmostlab.notes.ui.note;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;

import com.headmostlab.notes.model.Note;

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
    public void setCreateDate(Date date) {
        Note note = this.note.getValue();
        note.setCreationDate(date);
        this.note.setValue(note);
    }

    @Override
    public void share() {
        noteToShare.setValue(note.getValue());
    }

}
