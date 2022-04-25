package com.herdal.notepad.view.fragments.note

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.herdal.notepad.R
import com.herdal.notepad.adapter.NoteAdapter
import com.herdal.notepad.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.fragment_list_note.view.*


class ListNoteFragment : Fragment() {

    private lateinit var noteViewModel: NoteViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_list_note, container, false)

        // recylerView
        val adapter = NoteAdapter()
        val recyclerView = view.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)

        // note viewmodel
        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        noteViewModel.listAllNotes.observe(viewLifecycleOwner, Observer { note ->
            adapter.setData(note)
        })

        view.floatingActionButton.setOnClickListener { view ->
            findNavController().navigate(R.id.action_listNoteFragment_to_addNoteFragment)
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
            deleteAllNotes()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllNotes() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _,_ ->
            noteViewModel.deleteAllNotes()
            Toast.makeText(requireContext(),"Successfully removed every notes!",Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("No") {_,_ ->}
        builder.setTitle("This action cannot be undone.")
        builder.setMessage("Are you sure you want to delete all the notes?")
        builder.create().show()
    }
}