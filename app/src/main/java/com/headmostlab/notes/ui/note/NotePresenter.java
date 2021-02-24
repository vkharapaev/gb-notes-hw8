package com.headmostlab.notes.ui.note;

import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import com.headmostlab.notes.model.Note;

import java.lang.ref.WeakReference;
import java.util.Date;

public class NotePresenter extends ViewModel implements NoteContract.Presenter {

    private static final String NOTE_KEY = "NOTE";

    private final SavedStateHandle dataStorage;
    private WeakReference<NoteContract.View> view;
    private Note note;

    public NotePresenter(SavedStateHandle savedState) {
        dataStorage = savedState;
        note = dataStorage.get(NOTE_KEY);
    }

    @Override
    public void takeView(NoteContract.View view) {
        this.view = new WeakReference<>(view);
        showNote();
    }

    @Override
    public void setNote(Note note) {
        this.note = note;
        dataStorage.set(NOTE_KEY, note);
    }

    @Override
    public void setCreateDate(Date date) {
        note.setCreationDate(date);
        showNote();
    }

    @Override
    public void share() {
        if (view() != null) {
            view().share(note);
        }
    }

    private void showNote() {
        if (note != null && view() != null) {
            view().show(note);
        }
    }

    private NoteContract.View view() {
        if (view != null) {
            return view.get();
        }
        return null;
    }
}
