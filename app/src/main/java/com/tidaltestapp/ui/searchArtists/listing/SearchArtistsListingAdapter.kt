package com.tidaltestapp.ui.searchArtists.listing

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tidaltestapp.R
import com.tidaltestapp.data.model.artist.Artist
import com.tidaltestapp.utils.ImageLoader

/**
 * Created by naderbaltaji on 2020-01-29
 */
class SearchArtistsListingAdapter(val imageLoader: ImageLoader, val listener: SearchArtistListListener) : RecyclerView.Adapter<SearchArtistsListingAdapter.ArtistViewHolder>()  {

    val artistItems = ArrayList<Artist>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_artist, parent, false)
        return ArtistViewHolder(view)
    }

    override fun getItemCount(): Int {
        return artistItems.size
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        holder.bind(artistItems[position])
    }

    fun replaceItems(artistItems: List<Artist>) {
        this.artistItems.clear()
        this.artistItems.addAll(artistItems)
        notifyDataSetChanged()
    }

    fun clearItems() {
        this.artistItems.clear()
        notifyDataSetChanged()
    }

    inner class ArtistViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var ivArtist: ImageView = view.findViewById(R.id.iv_artist)
        var tvArtistName: TextView = view.findViewById(R.id.tv_artist_name)

        fun bind(artist: Artist) {
            // artist image
            imageLoader.loadCircularImage(artist.picture, ivArtist)
            // artist name
            tvArtistName.text = artist.name
            // click listener
            itemView.setOnClickListener{
                listener.onArtistSelected(artist)
            }
        }

    }

    interface SearchArtistListListener {
        fun onArtistSelected(artist: Artist)
    }
}