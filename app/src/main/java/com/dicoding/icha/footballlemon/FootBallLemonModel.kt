package com.dicoding.icha.footballlemon

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FootBallLemonModel(val club_name:String, val club_image:Int?, val club_detail:String):Parcelable