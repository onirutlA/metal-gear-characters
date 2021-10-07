package com.onirutla.metalgearcharacter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.onirutla.metalgearcharacter.data.Differentiator
import com.onirutla.metalgearcharacter.data.MetalGearCharacter
import com.onirutla.metalgearcharacter.databinding.CharacterItemBinding

class CharacterListAdapter(private val listener: (view: View, character: MetalGearCharacter) -> Unit) :
    ListAdapter<MetalGearCharacter, CharacterListAdapter.ViewHolder>(Differentiator) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharacterListAdapter.ViewHolder {
        val binding: CharacterItemBinding =
            CharacterItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterListAdapter.ViewHolder, position: Int) {
        holder.apply {
            binding.character = getItem(position)
            itemView.setOnClickListener {
                listener(it, getItem(position))
            }
        }
    }

    inner class ViewHolder(val binding: CharacterItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}