package com.example.superheroesapp.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class Result(
    val appearance: @RawValue Appearance,
    val biography: @RawValue Biography,
    val connections: @RawValue Connections,
    val id: String,
    val image: @RawValue Image,
    val name: String,
    val powerstats: @RawValue Powerstats,
    val work: @RawValue Work
) : Parcelable