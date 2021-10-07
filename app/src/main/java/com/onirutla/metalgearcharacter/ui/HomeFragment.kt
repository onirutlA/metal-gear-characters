package com.onirutla.metalgearcharacter.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.google.android.material.transition.MaterialElevationScale
import com.google.android.material.transition.MaterialSharedAxis
import com.onirutla.metalgearcharacter.CharacterListAdapter
import com.onirutla.metalgearcharacter.R
import com.onirutla.metalgearcharacter.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()

    private val listAdapter by lazy {
        CharacterListAdapter { view, character ->
            val characterCardDetailTransitionName = getString(R.string.list_transition_detail)
            val extras = FragmentNavigatorExtras(view to characterCardDetailTransitionName)

            exitTransition = MaterialElevationScale(false).apply {
                duration = resources.getInteger(R.integer.material_motion_duration_long_1).toLong()
            }
            reenterTransition = MaterialElevationScale(true).apply {
                duration = resources.getInteger(R.integer.material_motion_duration_long_1).toLong()
            }

            view.findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToDetailFragment(character),
                extras
            )
        }
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

        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }

        setUpUI()
        setUpMenu()
    }

    private fun setUpUI() {
        viewModel.characters.observe(viewLifecycleOwner, {
            listAdapter.submitList(it)
        })
        binding.characterList.apply {
            adapter = listAdapter
            setHasFixedSize(true)
        }
    }

    private fun setUpMenu() {
        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menu_about -> {

                    exitTransition = MaterialSharedAxis(MaterialSharedAxis.Z, true).apply {
                        duration = resources.getInteger(R.integer.material_motion_duration_long_1).toLong()
                    }
                    reenterTransition = MaterialSharedAxis(MaterialSharedAxis.Z, false).apply {
                        duration = resources.getInteger(R.integer.material_motion_duration_long_1).toLong()
                    }

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