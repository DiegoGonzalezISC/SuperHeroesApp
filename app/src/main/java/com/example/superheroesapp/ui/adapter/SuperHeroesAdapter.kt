package com.example.superheroesapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.superheroesapp.ui.fragments.SuperHeroesListFragmentDirections

import com.example.superheroesapp.data.Result
import com.example.superheroesapp.data.SuperHeroID
import com.example.superheroesapp.databinding.ItemSuperheroBinding
import com.example.superheroesapp.ui.viewmodel.SuperHeroesViewModel

class SuperHeroesAdapter(private val superhero: ArrayList<SuperHeroID?>, private val cellClickListener: CellClickListener) :
    RecyclerView.Adapter<SuperHeroesAdapter.SuperHeroViewHolder>() {

    inner class SuperHeroViewHolder(itemView: ItemSuperheroBinding) :
        RecyclerView.ViewHolder(itemView.root) {

        private val binding: ItemSuperheroBinding = itemView


        fun bindView(item: SuperHeroID?) {
            binding.tvSuperHeroName.text = item!!.name
            binding.tvSuperHeroRealName.text = item.biography.fullName
            Glide.with(itemView).load(item.image.url).into(binding.ivSuperHeroImg)

            binding.cvSuperHero.setOnClickListener {

                val direction = SuperHeroesListFragmentDirections.actionSuperHeroesListFragmentToSuperHeroDetailsFragment(item)
                cellClickListener.onCellClickListener()
                Navigation.findNavController(it).navigate(direction)
            }
        }
    }

    fun addSuperHero(superHeroes: ArrayList<SuperHeroID?>) {
        this.superhero.addAll(superHeroes)
        notifyDataSetChanged()
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