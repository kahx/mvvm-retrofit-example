package com.kaansa.nuevo.ui.Recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.kaansa.nuevo.R
import com.kaansa.nuevo.models.PhotoItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_list_item.view.*

class RecyclerAdapter(private val photos: ArrayList<PhotoItem>, private val onPhotoClickListener: OnPhotoClickListener) : RecyclerView.Adapter<RecyclerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_list_item, parent, false))
    }
    private lateinit var imageView : ImageView


    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val photo = photos[position]
        holder.itemView.photo_title.text = photo.title
        imageView = holder.itemView.photo_thumbnail

        Picasso.get().load(photo.thumbnailUrl)
            .into(imageView)
        holder.itemView.setOnClickListener {
            onPhotoClickListener.onPhotoItemClicked(position)
        }

    }

    override fun getItemCount(): Int {
        return 100
    }
}