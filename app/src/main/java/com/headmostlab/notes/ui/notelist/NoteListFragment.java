package com.headmostlab.notes.ui.notelist;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.headmostlab.notes.R;
import com.headmostlab.notes.databinding.FragmentNoteListBinding;
import com.headmostlab.notes.databinding.NoteRowItemBinding;
import com.headmostlab.notes.model.Note;
import com.headmostlab.notes.ui.note.NoteFragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NoteListFragment extends Fragment implements NoteListContract.View {

    public static final String NOTE_TAG = "NOTE";
    private FragmentNoteListBinding binding;
    private NoteListContract.Presenter presenter;
    private NoteListAdapter adapter;

    public static NoteListFragment newNoteListFragment() {
        return new NoteListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentNoteListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initRecyclerView();
        presenter = new ViewModelProvider(this,
                new NoteListViewModelFactory(this, null)).get(NoteListPresenter.class);
        presenter.takeView(this);
        presenter.setOrientation(getResources().getConfiguration().orientation);
    }

    private void initRecyclerView() {
        adapter = new NoteListAdapter(Collections.emptyList());
        binding.noteList.setAdapter(adapter);
        binding.noteList.addItemDecoration(new MyItemDecoration(requireActivity()));
    }

    @Override
    public void show(Note note) {
        boolean isPortrait = getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_PORTRAIT;

        if (isPortrait) {
            getParentFragmentManager()
                    .beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.container, NoteFragment.newNoteFragment(note), NOTE_TAG)
                    .addToBackStack(null)
                    .commit();
        } else {
            getChildFragmentManager()
                    .beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.childContainer, NoteFragment.newNoteFragment(note), NOTE_TAG)
                    .commit();
        }
    }

    @Override
    public void show(ArrayList<Note> notes) {
        adapter.setNotes(notes);
    }

    @Override
    public void closeNote() {
        removeFragment(getParentFragmentManager(), NOTE_TAG);
        removeFragment(getChildFragmentManager(), NOTE_TAG);
    }

    private void removeFragment(FragmentManager fm, String tag) {
        Fragment fragment = fm.findFragmentByTag(tag);
        if (fragment != null) {
            fm.beginTransaction().remove(fragment).commit();
        }
    }

    private class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.ViewHolder> {

        private List<Note> notes;

        public NoteListAdapter(List<Note> notes) {
            this.notes = notes;
        }

        @NonNull
        @Override
        public NoteListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                             int viewType) {
            NoteRowItemBinding binding =
                    NoteRowItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new ViewHolder(binding);
        }

        public void setNotes(List<Note> notes) {
            this.notes = notes;
            notifyDataSetChanged();
        }

        @Override
        public void onBindViewHolder(@NonNull NoteListAdapter.ViewHolder holder, int position) {
            holder.bind(notes.get(position));
        }

        @Override
        public int getItemCount() {
            return notes.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            private final NoteRowItemBinding binding;

            public ViewHolder(NoteRowItemBinding binding) {
                super(binding.getRoot());
                this.binding = binding;
            }

            void bind(Note note) {
                binding.title.setText(note.getTitle());
                binding.itemContainer.setOnClickListener(v -> presenter.selectNote(note));
            }
        }
    }
}
