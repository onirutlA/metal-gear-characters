package com.onirutla.metalgearcharacter.data

import androidx.recyclerview.widget.DiffUtil

object Differentiator : DiffUtil.ItemCallback<MetalGearCharacter>() {

    override fun areItemsTheSame(
        oldItem: MetalGearCharacter,
        newItem: MetalGearCharacter
    ): Boolean = oldItem.name == newItem.name


    override fun areContentsTheSame(
        oldItem: MetalGearCharacter,
        newItem: MetalGearCharacter
    ): Boolean = oldItem == newItem

}