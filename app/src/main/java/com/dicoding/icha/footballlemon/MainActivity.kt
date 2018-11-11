package com.dicoding.icha.footballlemon

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView


class MainActivity : AppCompatActivity() {
    private val listClub:MutableList<FootBallLemonModel> = mutableListOf()

    class MainActivityUI: AnkoComponent<MainActivity>{
        override fun createView(ui: AnkoContext<MainActivity>): View = with(ui) {
            verticalLayout {
                lparams(matchParent, matchParent)
                recyclerView{
                    id = R.id.recycler_club
                }.lparams(width = matchParent, height = matchParent)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivityUI().setContentView(this)

        val recyclerViewClub = find<RecyclerView>(R.id.recycler_club)

        initClubData()
        recyclerViewClub.layoutManager = LinearLayoutManager(this)
        recyclerViewClub.adapter = FootBallLemonAdapter(listClub){
            val toast = Toast.makeText(applicationContext, it.club_name, Toast.LENGTH_SHORT)
            toast.show()
            startActivity<DetailActivity>(CLUB to it)
        }
    }

    private fun initClubData(){
        val clubName = resources.getStringArray(R.array.club_name)
        val clubImage =  resources.obtainTypedArray(R.array.club_image)
        val clubDetail = resources.getStringArray(R.array.club_detail)

        listClub.clear()

        for (i in clubName.indices){
            listClub.add(FootBallLemonModel(clubName[i], clubImage.getResourceId(i,0), clubDetail[i]))
        }
    }
}
