package com.example.superheroesapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.superheroesapp.data.*
import com.example.superheroesapp.databinding.*
import com.example.superheroesapp.ui.view.MainActivity


class SuperHeroDetailsFragment : Fragment() {
    private lateinit var binding: FragmentSuperHeroDetailsBinding
    private  val args: SuperHeroDetailsFragmentArgs by navArgs<SuperHeroDetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = FragmentSuperHeroDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        var ab = (requireActivity() as MainActivity).supportActionBar!!
        ab.show()
        ab.title = args.detailsSuperHero.name


        var powerstats = args.detailsSuperHero.powerstats
        var biography = args.detailsSuperHero.biography
        var appearance = args.detailsSuperHero.appearance
        var work = args.detailsSuperHero.work
        var connections = args.detailsSuperHero.connections

        Glide.with(binding.root).load(args.detailsSuperHero.image.url).into(binding.ivSuperHeroImg)
        binding.tvSuperHeroId.text = args.detailsSuperHero.id
        binding.tvHeroName.text = args.detailsSuperHero.name

//        View binding Layouts
        setValuesPowerStats(powerstats, view)
        setValuesBiography(biography, view)
        setValuesAppearance(appearance, view)
        setValuesWork(work, view)
        setValuesConnections(connections, view)

    }



    // ----------- PowerStats -------------
    private fun setValuesPowerStats(powerstats: Powerstats, view: View) {
        var bindPowerstatsLayoutBinding: SuperheroPowerstatsLayoutBinding = SuperheroPowerstatsLayoutBinding.bind(view)
        bindPowerstatsLayoutBinding.tvPowerStatsIntelligenceContent.text = powerstats.intelligence
        bindPowerstatsLayoutBinding.tvPowerStatsStrengthContent.text = powerstats.strength
        bindPowerstatsLayoutBinding.tvPowerStatsSpeedContent.text = powerstats.speed
        bindPowerstatsLayoutBinding.tvPowerStatsDurabilityContent.text = powerstats.durability
        bindPowerstatsLayoutBinding.tvPowerStatsPowerContent.text = powerstats.power
        bindPowerstatsLayoutBinding.tvPowerStatsCombatContent.text = powerstats.combat
    }

    // ----------- BIOGRAPHY -------------
    private fun setValuesBiography(biography: Biography, view: View) {

        var bindBiographyLayoutBinding: SuperheroBiographyLayoutBinding = SuperheroBiographyLayoutBinding.bind(view)
        bindBiographyLayoutBinding.tvBiographyFullnameContent.text = biography.fullName
        bindBiographyLayoutBinding.tvBiographyAlterEgosContent.text = biography.alterEgos
        bindBiographyLayoutBinding.tvBiographyPlaceOfBirthContent.text = biography.placeOfBirth
        bindBiographyLayoutBinding.tvBiographyFirstAppearanceContent.text = biography.firstAppearance
        bindBiographyLayoutBinding.tvBiographyPublisherContent.text = biography.publisher
        bindBiographyLayoutBinding.tvBiographyAlignmentContent.text = biography.alignment
        // Biography Aliases
        var aliases = biography.aliases?: emptyList()
        var aliasesStr: String = ""
        aliases.forEachIndexed { index, item ->
            // ...
            aliasesStr += if (index == aliases.size-1)"$item" else "${item}\n"
        }
        if (aliasesStr.length == 0) {
            aliasesStr = "No aliases found."
        }
        bindBiographyLayoutBinding.tvBiographyAliasesContent.text = aliasesStr
    }

    // ----------- Appearance -------------
    private fun setValuesAppearance(appearance: Appearance, view: View) {

        var bindAppearanceLayoutBinding: SuperheroAppearanceLayoutBinding = SuperheroAppearanceLayoutBinding.bind(view)
        bindAppearanceLayoutBinding.tvAppearanceGenderContent.text = appearance.gender
        bindAppearanceLayoutBinding.tvAppearanceRaceContent.text = appearance.race
        bindAppearanceLayoutBinding.tvAppearanceEyeColorContent.text = appearance.eyeColor
        bindAppearanceLayoutBinding.tvAppearanceHairColorContent.text = appearance.hairColor
        // Appearance height
        var height = appearance.height?: emptyList()
        var heightStr: String = ""
        height.forEachIndexed { index, item ->
            // ...
            heightStr += if (index == height.size-1)"$item" else "${item}|"
        }
        if (heightStr.length == 0) {
            heightStr = "No height found."
        }
        bindAppearanceLayoutBinding.tvAppearanceHeightContent.text = heightStr
        // Appearance weight
        var weight = appearance.weight?: emptyList()
        weight = appearance.weight?: emptyList()
        var weightStr: String = ""
        weight.forEachIndexed { index, item ->
            // ...
            weightStr += if (index == weight.size-1)"$item" else "${item}|"
        }
        if (weightStr.length == 0) {
            weightStr = "No height found."
        }
        bindAppearanceLayoutBinding.tvAppearanceWeightContent.text = weightStr
    }

    // ----------- Work -------------
    private fun setValuesWork(work: Work, view: View) {
        var bindWorkLayoutBinding: SuperheroWorkLayoutBinding = SuperheroWorkLayoutBinding.bind(view)
        bindWorkLayoutBinding.tvWorkOccupationContent.text = work.occupation
        bindWorkLayoutBinding.tvWorkBaseContent.text = work.base
    }

    // -----------Connections -------------
    private fun setValuesConnections(connections: Connections, view: View) {
        var bindConnectionsLayoutBinding: SuperheroConnectionsLayoutBinding = SuperheroConnectionsLayoutBinding.bind(view)
        bindConnectionsLayoutBinding.tvConnectionsGroupAffiliationContent.text = connections.groupAffiliation
        bindConnectionsLayoutBinding.tvConnectionsRelativesContent.text = connections.relatives

    }

}