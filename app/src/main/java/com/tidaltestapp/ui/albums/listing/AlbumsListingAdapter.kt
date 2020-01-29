package com.tidaltestapp.ui.albums.listing

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tidaltestapp.R
import com.tidaltestapp.data.model.artistAlbums.Album
import com.tidaltestapp.utils.ImageLoader

/**
 * Created by naderbaltaji on 2020-01-29
 */
class AlbumsListingAdapter(val imageLoader: ImageLoader, val artistName: String, val listener: AlbumListingListener) : RecyclerView.Adapter<AlbumsListingAdapter.AlbumViewHolder>() {
    val albums =  ArrayList<Album>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_album, parent, false)
        return AlbumViewHolder(view)
    }

    override fun getItemCount(): Int {
        return albums.size
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.bind(albums[position])
    }

    fun addItems(albums: List<Album>) {
        this.albums.addAll(albums)
        notifyDataSetChanged()
    }

    inner class AlbumViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val ivAlbum = view.findViewById<ImageView>(R.id.iv_album)
        private val tvAlbumTitle = view.findViewById<TextView>(R.id.tv_album_title)
        private val tvArtistName = view.findViewById<TextView>(R.id.tv_artist_name)

        fun bind(album: Album) {
            // cover
            imageLoader.loadImage(album.coverXl, ivAlbum)
            // album title
            tvAlbumTitle.text = album.title
            // artist name
            tvArtistName.text = artistName
            // click listener
            itemView.setOnClickListener{
                listener.onAlbumSelected(album)
            }
        }

    }

    interface AlbumListingListener {
        fun onAlbumSelected(album: Album)
    }
}