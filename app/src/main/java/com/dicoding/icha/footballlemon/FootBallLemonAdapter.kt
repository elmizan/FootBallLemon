package com.dicoding.icha.footballlemon

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import org.jetbrains.anko.*

class FootBallLemonAdapter constructor(private val listClub : List<FootBallLemonModel>, private val listenerClub: (FootBallLemonModel)->Unit):RecyclerView.Adapter<FootBallLemonAdapter.FootBallViewHolder>(){
    class FootBallViewHolderUI : AnkoComponent<Context>{
        override fun createView(ui: AnkoContext<Context>): View = with(ui){
            linearLayout {
                padding = dip(16)
                lparams(width = matchParent, height = wrapContent)
                imageView{
                    id = R.id.club_image
                    imageResource = R.drawable.ic_launcher_background
                }.lparams(width = dip(50), height = dip(50))

                textView{
                    id = R.id.club_name
                    text = resources.getString(R.string.club_name_dummy)
                    textSize = 20f
                    textColor = Color.BLACK
                    topPadding = dip(10)
                    leftPadding = dip(10)
                }.lparams(width = wrapContent, height = wrapContent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FootBallLemonAdapter.FootBallViewHolder {
        return FootBallViewHolder(FootBallViewHolderUI().createView(AnkoContext.create(parent.context, false)))
    }

    override fun getItemCount(): Int {
        return listClub.size
    }

    override fun onBindViewHolder(footballViewHolder: FootBallViewHolder, position: Int) {
        footballViewHolder.bind(listClub[position], listenerClub)
    }

    inner class FootBallViewHolder(override val containerView: View): RecyclerView.ViewHolder(containerView),
            LayoutContainer{
        private val clubName = containerView.find<TextView>(R.id.club_name)
        private val clubImage = containerView.find<ImageView>(R.id.club_image)

        fun bind(club:FootBallLemonModel, listenerClub: (FootBallLemonModel) -> Unit){
            clubName.text = club.club_name
            club.club_image?.let { Picasso.get().load(it).resize(100,100).centerInside().into(clubImage) }
            containerView.setOnClickListener {
                listenerClub(club)
            }

        }
    }
}