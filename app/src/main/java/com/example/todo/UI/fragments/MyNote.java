package com.example.todo.UI.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.todo.R;
import com.example.todo.entities.noteEntity;
import com.example.todo.repository.noteRepository;
import com.example.todo.utils.RecyclerItemClickListener;

import java.util.List;

import javax.annotation.Nullable;


public class MyNote extends Fragment implements RecyclerItemClickListener.OnRecyclerViewItemClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerNote;
    private ImageView imgFly;
    private TextView txtEmpty;
    private NoteAdapter noteAdapter;

    private com.example.todo.repository.noteRepository noteRepository;


    private OnFragmentInteractionListener mListener;

    public MyNote() {
        // Required empty public constructor
    }

    public static MyNote newInstance(String param1, String param2) {
        MyNote fragment = new MyNote();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        //noteRepository = new noteRepository(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_my_note, container, false);
        initComponents(v);
        //updateNoteList();
        return v;
    }

    /*private void updateNoteList() {
        noteRepository.getTasks().observe(this, new Observer<List<noteEntity>>() {
            @Override
            public void onChanged(@Nullable List<noteEntity> notes) {
                if(notes.size() > 0) {
                    txtEmpty.setVisibility(View.GONE);
                    recyclerNote.setVisibility(View.VISIBLE);
                    if (noteAdapter == null) {
                        noteAdapter = new NoteAdapter(notes, getContext());
                        recyclerNote.setAdapter(noteAdapter);

                    } else noteAdapter.addNotes(notes);
                } else updateEmptyView();
            }
        });
    }*/

    private void updateEmptyView() {
        txtEmpty.setVisibility(View.VISIBLE);
        imgFly.setVisibility(View.VISIBLE);
        recyclerNote.setVisibility(View.GONE);
    }

    private void initComponents(View v) {
        recyclerNote = v.findViewById(R.id.recyclerToDo);
        recyclerNote.setLayoutManager(new LinearLayoutManager(getContext()));
        //recyclerNote.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), getContext()));
        imgFly = v.findViewById(R.id.imgFly);
        txtEmpty = v.findViewById(R.id.txtEmpy);
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemClick(View parentView, View childView, int position) {
        noteEntity note = noteAdapter.getItem(position);



    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
