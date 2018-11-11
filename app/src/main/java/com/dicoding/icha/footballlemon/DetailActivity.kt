package com.dicoding.icha.footballlemon

import android.graphics.Color
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*

class DetailActivity: AppCompatActivity(){

    class DetailActivityUI : AnkoComponent<DetailActivity>{
        override fun createView(ui: AnkoContext<DetailActivity>): View = with(ui){
            verticalLayout {
                imageView{
                    id = R.id.club_image
                    setImageResource(R.drawable.ic_launcher_background)
                    padding = dip(20)
                }.lparams(width = matchParent)

                textView{
                    text = resources.getString(R.string.club_name_dummy)
                    id = R.id.club_name
                    textSize = 20f
                    textColor= Color.BLACK
                    textAlignment = View.TEXT_ALIGNMENT_CENTER
                }.lparams(matchParent, wrapContent)

                textView{
                    text = resources.getString(R.string.club_detail_dummy)
                    id = R.id.club_detail
                    textSize = 14f
                    textColor = Color.BLACK
                    textAlignment = View.TEXT_ALIGNMENT_CENTER
                }.lparams(matchParent, wrapContent)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DetailActivityUI().setContentView(this)

        val clubIntent: FootBallLemonModel = intent.getParcelableExtra(CLUB)
        val clubImage = find<ImageView>(R.id.club_image)
        val clubName = find<TextView>(R.id.club_name)
        val clubDetail = find<TextView>(R.id.club_detail)

        clubIntent.club_image?.let { Picasso.get().load(it).resize(dip(100),dip(100)).centerInside().into(clubImage) }
        clubName.text = clubIntent.club_name
        clubDetail.text = clubIntent.club_detail
    }
}