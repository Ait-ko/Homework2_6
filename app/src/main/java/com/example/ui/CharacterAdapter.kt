package com.example.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homework2_6.R
import com.example.homework2_6.databinding.ItemCharacterBinding


class CharacterAdapter(
    private val onClick: (character: Character) -> Unit,
    private var list: List<Character>
) : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            ItemCharacterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun submitList(list: List<Character>) {
        this.list = list
    }

    inner class CharacterViewHolder(private val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("ResourceAsColor")
        fun bind(character: Character)  {
            Glide.with(binding.image).load(character.image)
                .into(binding.image)
            binding.tvName.text = character.name
            binding.tvStatus.text = character.status
            binding.tvSpecies.text = character.species
            binding.tvLocation.text = character.location.name
            itemView.setOnClickListener {
                onClick( character)
            }


    when (character.status) {
        "Alive" -> binding.imgCircleStatus.setBackgroundResource(R.drawable.alive)
        "Dead" -> binding.imgCircleStatus.setBackgroundResource(R.drawable.death)
        "Unknown" -> binding.imgCircleStatus.setBackgroundResource(R.drawable.unknown)
    }

        }

    }
}