package com.headmostlab.notes.ui.notelist;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.AbstractSavedStateViewModelFactory;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.savedstate.SavedStateRegistryOwner;

public class NoteListViewModelFactory extends AbstractSavedStateViewModelFactory
        implements ViewModelProvider.Factory {

    public NoteListViewModelFactory(@NonNull SavedStateRegistryOwner owner, @Nullable Bundle defaultArgs) {
        super(owner, defaultArgs);
    }

    @NonNull
    @Override
    protected <T extends ViewModel> T create(@NonNull String key, @NonNull Class<T> modelClass, @NonNull SavedStateHandle handle) {
        return (T) new NoteListViewModelImpl(handle);
    }
}
