package com.herdal.notepad.view

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.herdal.notepad.R
import com.herdal.notepad.model.Note
import com.herdal.notepad.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.fragment_add_note.*
import kotlinx.android.synthetic.main.fragment_add_note.view.*


class AddNoteFragment : Fragment() {

    private lateinit var noteViewModel: NoteViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_add_note, container, false)

        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        view.button_add_note.setOnClickListener {
            insertNoteToDatabase()
        }

        return view
    }

    private fun insertNoteToDatabase() {
        val title = editTextNoteTitle.text.toString()
        val note = editTextNote.text.toString()
        if(inputCheck(title,note)) { // create a note
            val note = Note(0,title,note)
            // add to db
            noteViewModel.addNote(note)
            Toast.makeText(requireContext(),"Successfully created!",Toast.LENGTH_SHORT).show()
            // navigate to list
            findNavController().navigate(R.id.action_addNoteFragment_to_listNoteFragment)
        }
        else {
            Toast.makeText(requireContext(),"Please fill title and note field.",Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(title: String, note: String) : Boolean {
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(note))
    }
}