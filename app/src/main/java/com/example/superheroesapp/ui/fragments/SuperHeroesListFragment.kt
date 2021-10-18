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
import com.example.superheroesapp.databinding.FragmentSuperHeroesListBinding
import com.example.superheroesapp.ui.adapter.SuperHeroesAdapter
import com.example.superheroesapp.data.Result
import com.example.superheroesapp.ui.view.MainActivity
import com.example.superheroesapp.ui.viewmodel.SuperHeroesViewModel


class SuperHeroesListFragment : Fragment(), SearchView.OnQueryTextListener {


    private lateinit var binding: FragmentSuperHeroesListBinding
    lateinit var viewModel: SuperHeroesViewModel
    lateinit var superHeroAdapter: SuperHeroesAdapter

    var busqueda = "a"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let { context ->
            viewModel = (context as MainActivity).superHeroViewModel
        }
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
        viewModel.onCreate(busqueda)
        viewModel.superHeroModel.observe(viewLifecycleOwner) { response ->
            initHeroes(response)
        }

        binding.svSuperHeroes.setOnQueryTextListener(this)
    }

    private fun initHeroes(response: List<Result>) {
        superHeroAdapter = SuperHeroesAdapter(response)
        binding.rvSuperHeroes.apply {
            adapter = superHeroAdapter
            layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            isNestedScrollingEnabled = false
        }
    }

    private fun searchSuperHeroes(searchStr: String) {
        busqueda = searchStr
        viewModel.onCreate(busqueda)
        superHeroAdapter.notifyDataSetChanged()
    }



    fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }


    fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }



    override fun onQueryTextSubmit(query: String?): Boolean {

        if(!query.isNullOrEmpty()) {
            searchSuperHeroes(query.lowercase())
            hideKeyboard()
        }

        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }


}