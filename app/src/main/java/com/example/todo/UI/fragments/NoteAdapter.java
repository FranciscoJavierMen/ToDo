package com.example.todo.UI.fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo.entities.noteEntity;
import com.example.todo.R;

import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> implements RealmChangeListener {

    private final RealmResults<noteEntity> mNoteEntities;
    private Context context;

    public NoteAdapter(RealmResults<noteEntity> mNoteEntities, Context context) {
        this.mNoteEntities = mNoteEntities;
        this.context = context;
        mNoteEntities.addChangeListener(this);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View v = layoutInflater.inflate(R.layout.item_note, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(mNoteEntities.get(position).getTitle());
        holder.note.setText(mNoteEntities.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        if (mNoteEntities != null){
            return mNoteEntities.size();
        }
        return 0;
    }

    @Override
    public void onChange(Object o) {
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title, note;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.txtTitle);
            note = itemView.findViewById(R.id.txtNote);
        }
    }
}
