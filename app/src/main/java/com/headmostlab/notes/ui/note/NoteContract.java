package com.headmostlab.notes.ui.note;

import com.headmostlab.notes.model.Note;

import java.util.Date;

public interface NoteContract {
    interface View {
        void show(Note note);

        void share(Note note);
    }

    interface Presenter {
        void takeView(View view);

        void setNote(Note note);

        void setCreateDate(Date selection);

        void share();
    }
}
