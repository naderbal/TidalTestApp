package com.tidaltestapp.ui.tracks.listing

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tidaltestapp.R
import com.tidaltestapp.data.model.albumTracks.Track

/**
 * Created by naderbaltaji on 2020-01-29
 */
class TracksOfAlbumListingAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    val items = ArrayList<TrackOfAlbumListingItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when(viewType) {
            TrackOfAlbumListingType.VOLUME.ordinal -> {
                val view = inflater.inflate(R.layout.row_tracks_volume, parent, false)
                VolumeViewHolder(view)
            }
            TrackOfAlbumListingType.TRACK.ordinal -> {
                val view = inflater.inflate(R.layout.row_track, parent, false)
                TrackViewHolder(view)
            }
            else -> {
                val view = inflater.inflate(R.layout.row_track, parent, false)
                TrackViewHolder(view)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].trackOfAlbumListingType.ordinal
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val listingType = items[position].trackOfAlbumListingType
        when(listingType) {
            TrackOfAlbumListingType.VOLUME -> {
                val volumeListingItem = items[position] as VolumeListingItem
                (holder as VolumeViewHolder).bind(volumeListingItem.volume)
            }
            TrackOfAlbumListingType.TRACK -> {
                val trackListingItem = items[position] as TrackListingItem
                (holder as TrackViewHolder).bind(trackListingItem.track, trackListingItem.trackNumber)
            }
        }
    }

    fun addListingItems(items: List<TrackOfAlbumListingItem>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    inner class TrackViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvTrackNumber = view.findViewById<TextView>(R.id.tv_track_nb)
        private val tvTrackTitle = view.findViewById<TextView>(R.id.tv_track_title)
        private val tvArtistName = view.findViewById<TextView>(R.id.tv_artist_name)

        fun bind(track: Track, trackNb: Int) {
            tvTrackNumber.text = trackNb.toString()
            tvTrackTitle.text = track.title
            track.artist?.let {
                tvArtistName.text = it.name
            }
        }
    }

    inner class VolumeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvVolume = view.findViewById<TextView>(R.id.tv_volume)

        fun bind(volume: String) {
            tvVolume.text = volume
        }
    }
}