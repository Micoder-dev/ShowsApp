package com.rasenyer.showsapp.ui.activities

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.rasenyer.showsapp.R
import com.rasenyer.showsapp.data.models.Show
import com.rasenyer.showsapp.databinding.ItemShowBinding

class ShowAdapter: RecyclerView.Adapter<ShowAdapter.MyShowViewHolder>() {

    private var shows: List<Show> = ArrayList()
    private lateinit var onCardViewClick: OnCardViewClick

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyShowViewHolder {
        return MyShowViewHolder(ItemShowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyShowViewHolder, position: Int) {

        val show = shows[position]

        holder.binding.apply {

            mImage.load(show.image?.original) {
                placeholder(R.color.purple_200)
                error(R.color.purple_200)
                crossfade(true)
                crossfade(400)
            }
            mName.text = show.name

        }

        holder.binding.mCardView.setOnClickListener {
            onCardViewClick.onClick(show)
        }

    }

    override fun getItemCount(): Int {
        return shows.size
    }

    class MyShowViewHolder(val binding: ItemShowBinding): RecyclerView.ViewHolder(binding.root)

    fun setShows(shows: List<Show>) {
        this.shows = shows
    }


    interface OnCardViewClick {
        fun onClick(show: Show)
    }

    fun setOnCardViewClick(onCardViewClick: OnCardViewClick) {
        this.onCardViewClick = onCardViewClick
    }


}