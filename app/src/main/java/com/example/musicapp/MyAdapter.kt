package com.example.musicapp

import android.app.Activity
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso

class MyAdapter(private val context : Activity, private val list: List<Data>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image = itemView.findViewById<ShapeableImageView>(R.id.ivImage)
        val textView: TextView = itemView.findViewById<TextView>(R.id.tvText)
        val pause = itemView.findViewById<ImageView>(R.id.ivPause)
        val Play = itemView.findViewById<ImageView>(R.id.ivPlay)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = list[position]

        //to set the text
        holder.textView.text = currentItem.title

        // to put image link into image view,picasso
        Picasso.get().load(currentItem.album.cover).into(holder.image);

        //to set the music
        val mediaPlayer= MediaPlayer.create(context, currentItem.preview.toUri())
        holder.Play.setOnClickListener{
            mediaPlayer.start()
        }
        holder.pause.setOnClickListener {
            mediaPlayer.stop()
        }

    }
}