package com.headmostlab.notes.ui.notelist;

import com.headmostlab.notes.model.Note;

import java.util.ArrayList;

public interface NoteListContract {
    interface View {
        void show(Note note);

        void show(ArrayList<Note> notes);

        void closeNote();
    }

    interface Presenter {
        void takeView(View view);

        void selectNote(Note note);

        void setOrientation(int orientation);
    }
}
