package com.herdal.notepad.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.herdal.notepad.R
import com.herdal.notepad.databinding.FragmentListNoteBinding
import kotlinx.android.synthetic.main.fragment_list_note.view.*


class ListNoteFragment : Fragment() {

    private lateinit var binding: FragmentListNoteBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_list_note, container, false)

        view.floatingActionButton.setOnClickListener { view ->
            findNavController().navigate(R.id.action_listNoteFragment_to_addNoteFragment)
        }

        return view
    }
}