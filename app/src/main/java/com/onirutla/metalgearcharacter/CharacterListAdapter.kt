package com.onirutla.metalgearcharacter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.onirutla.metalgearcharacter.data.MetalGearCharacter
import com.onirutla.metalgearcharacter.data.Differentiator
import com.onirutla.metalgearcharacter.databinding.CharacterItemBinding

class CharacterListAdapter :
    ListAdapter<MetalGearCharacter, CharacterListAdapter.ViewHolder>(Differentiator) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharacterListAdapter.ViewHolder {
        val binding: CharacterItemBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.character_item,
                parent,
                false
            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterListAdapter.ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: CharacterItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MetalGearCharacter) {
            binding.apply {
                character = item
                Glide.with(itemView.context).load(item.image).into(characterImage)
            }
        }
    }
}