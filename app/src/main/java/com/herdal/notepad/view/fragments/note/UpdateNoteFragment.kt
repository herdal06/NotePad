package com.herdal.notepad.view.fragments.note

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.herdal.notepad.R
import com.herdal.notepad.model.Note
import com.herdal.notepad.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.fragment_update_note.*
import kotlinx.android.synthetic.main.fragment_update_note.view.*

class UpdateNoteFragment : Fragment() {

    private val args by navArgs<UpdateNoteFragmentArgs>()

    private lateinit var noteViewModel: NoteViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_update_note, container, false)

        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        view.editTextUpdateNoteTitle.setText(args.currentNote.title)
        view.editTextUpdateNote.setText(args.currentNote.content)

        view.button_update_note.setOnClickListener {
            updateNote()
        }

        // add menu
        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete) {
            deleteSelectedNote()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteSelectedNote() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _,_ ->
            noteViewModel.deleteNote(args.currentNote)
            Toast.makeText(requireContext(),"Successfully removed the note!",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateNoteFragment_to_listNoteFragment)
        }
        builder.setNegativeButton("No") {_,_ ->}
        builder.setTitle("This action cannot be undone.")
        builder.setMessage("Are you sure you want to delete this note?")
        builder.create().show()

    }

    private fun updateNote() {
        val noteTitle = editTextUpdateNoteTitle.text.toString()
        val noteContent = editTextUpdateNote.text.toString()

        if(inputCheck(noteTitle,noteContent)) {
            // create updated note object
            val updatedNote = Note(args.currentNote.id,noteTitle,noteContent)
            // update current user
            noteViewModel.updateNote(updatedNote)
            Toast.makeText(requireContext(),"Successfully edited the note!",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateNoteFragment_to_listNoteFragment)
        }
        else {
            Toast.makeText(requireContext(),"Please fill title and note field.",Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(title: String, note: String) : Boolean {
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(note))
    }
}