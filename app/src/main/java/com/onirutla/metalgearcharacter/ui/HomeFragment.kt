package com.onirutla.metalgearcharacter.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.onirutla.metalgearcharacter.CharacterListAdapter
import com.onirutla.metalgearcharacter.R
import com.onirutla.metalgearcharacter.data.metalGearCharacters
import com.onirutla.metalgearcharacter.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val listAdapter by lazy { CharacterListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUI()
        setUpMenu()
    }

    private fun setUpUI() {
        listAdapter.submitList(metalGearCharacters)
        binding.characterList.apply {
            adapter = listAdapter
            setHasFixedSize(true)
        }
    }

    private fun setUpMenu() {
        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menu_about -> {
                    findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToAboutFragment())
                    true
                }
                else -> false
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}