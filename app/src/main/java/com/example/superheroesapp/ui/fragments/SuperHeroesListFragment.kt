package com.example.superheroesapp.ui.fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.superheroesapp.databinding.FragmentSuperHeroesListBinding
import com.example.superheroesapp.ui.adapter.SuperHeroesAdapter
import com.example.superheroesapp.data.Result
import com.example.superheroesapp.data.SuperHeroID
import com.example.superheroesapp.ui.adapter.CellClickListener
import com.example.superheroesapp.ui.view.MainActivity
import com.example.superheroesapp.ui.viewmodel.SuperHeroesViewModel


class SuperHeroesListFragment : Fragment(), CellClickListener {


    private lateinit var binding: FragmentSuperHeroesListBinding

    lateinit var viewModel: SuperHeroesViewModel
    lateinit var superHeroAdapter: SuperHeroesAdapter
    lateinit var layoutManager: LinearLayoutManager


    var page = 1

    var busqueda = "batman"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let { context ->
            viewModel = (context as MainActivity).superHeroViewModel
        }
        page = 1
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentSuperHeroesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).supportActionBar!!.hide()

        viewModel.nextPage()
        viewModel.currenPage.observe(viewLifecycleOwner) { response ->

            if(response > 1) {
                superHeroAdapter.addSuperHero(viewModel.getSuperHeroes())
                binding.progressBar.visibility = View.GONE
            }else {
                initHeroes(viewModel.getSuperHeroes())
            }
        }

    }

    private fun initHeroes(response: ArrayList<SuperHeroID?>) {

        superHeroAdapter = SuperHeroesAdapter(response, this)
        binding.rvSuperHeroes.apply {
            adapter = superHeroAdapter
            layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
        }
        initScroll()
        superHeroAdapter.notifyDataSetChanged()
        binding.progressBar.visibility = View.GONE
    }


    fun initScroll() {

        layoutManager = LinearLayoutManager(this.context)
        binding.rvSuperHeroes.layoutManager = layoutManager
        binding.rvSuperHeroes.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                val visibleItems: Int = layoutManager.childCount
                val pastItems = layoutManager.findFirstCompletelyVisibleItemPosition()
                val total =  superHeroAdapter.itemCount

                if(!viewModel.getLoadingState()){

                    if((visibleItems + pastItems) >= total){
                        binding.progressBar.visibility = View.VISIBLE
                        viewModel.nextPage()

                    }
                }

                super.onScrolled(recyclerView, dx, dy)

            }

        })
    }

    override fun onCellClickListener() {
        viewModel.page = 0
    }


}