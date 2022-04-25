package com.herdal.notepad.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.herdal.notepad.R
import com.herdal.notepad.model.Note
import com.herdal.notepad.view.fragments.note.ListNoteFragmentDirections
import kotlinx.android.synthetic.main.note_row.view.*

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    private var noteList = emptyList<Note>()

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.note_row,parent,false))
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = noteList.get(position)
        holder.itemView.textView_row_NoteTitle.text = note.title
        holder.itemView.textView_row_NoteContent.text = note.content
        holder.itemView.textView_row_CreationDate.text = note.creationDate

        // navigate list to update fragment
        holder.itemView.rowLayout.setOnClickListener {
            val action = ListNoteFragmentDirections.actionListNoteFragmentToUpdateNoteFragment(note)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    fun setData(note:List<Note>) {
        this.noteList = note
        notifyDataSetChanged()
    }
}