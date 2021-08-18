package com.evaneos.evaneostest.ui.adapters

import android.content.Context
import android.content.Intent
import android.graphics.drawable.LayerDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.evaneos.data.model.Destination
import com.evaneos.evaneostest.R
import com.evaneos.evaneostest.ui.activity.Show_Dest_WebView
import com.squareup.picasso.Picasso

class DestinationDataAdapter(
    mContext: Context,
    destinationsList: List<Destination>?
): RecyclerView.Adapter<RecyclerView.ViewHolder?>(){

    private val contexte: Context = mContext
    private val destinationList: List<Destination>? = destinationsList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(contexte).inflate(R.layout.main_activity_list_destination, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder)
        val destinations: Destination? = destinationList?.get(position)
        holder.nom.text = destinations?.name
        val stars = holder.rating.progressDrawable as LayerDrawable
        holder.rating.rating= destinations?.rating!!.toFloat()
        holder.tag.text = destinations.tag

        Picasso.get().load(destinations.pictureUrl).placeholder(R.drawable.default_image).into(holder.picture)


        holder.itemView.setOnClickListener {

            val intent_dest = Intent(contexte, Show_Dest_WebView::class.java)
            intent_dest.putExtra("destinationid", destinations.id)
            contexte.startActivity(intent_dest)

        }

    }

    override fun getItemCount(): Int {
        if (destinationList != null) {
            return destinationList.size
        }
        Log.d("message", destinationList.toString())
        return 0
    }

    class ViewHolder (objet: View) : RecyclerView.ViewHolder(objet) {
        var nom: TextView = objet.findViewById(R.id.item_title)
        var picture: ImageView = objet.findViewById(R.id.item_image)
        var rating: RatingBar = objet.findViewById(R.id.item_rating)
        var tag: TextView = objet.findViewById(R.id.item_tag)


    }
}