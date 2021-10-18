package com.example.superheroesapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.superheroesapp.ui.fragments.SuperHeroesListFragmentDirections

import com.example.superheroesapp.data.Result
import com.example.superheroesapp.databinding.ItemSuperheroBinding

class SuperHeroesAdapter(private val superhero: List<Result>) :
    RecyclerView.Adapter<SuperHeroesAdapter.SuperHeroViewHolder>() {

    inner class SuperHeroViewHolder(itemView: ItemSuperheroBinding) :
        RecyclerView.ViewHolder(itemView.root) {

        private val binding: ItemSuperheroBinding = itemView

        fun bindView(item: Result) {
            binding.tvSuperHeroName.text = item.name
            binding.tvSuperHeroRealName.text = item.biography.fullName
            Glide.with(itemView).load(item.image.url).into(binding.ivSuperHeroImg)

            binding.cvSuperHero.setOnClickListener {

                val direction = SuperHeroesListFragmentDirections.actionSuperHeroesListFragmentToSuperHeroDetailsFragment(item)
                Navigation.findNavController(it).navigate(direction)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {
        val binding: ItemSuperheroBinding =
            ItemSuperheroBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SuperHeroViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SuperHeroViewHolder, position: Int) {
        holder.bindView(superhero[position])
    }

    override fun getItemCount(): Int = superhero.size

}