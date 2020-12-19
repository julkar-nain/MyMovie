package com.julkar.mymovie.presentation.movielist.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.julkar.mymovie.R
import com.julkar.mymovie.domain.Movie
import com.julkar.mymovie.presentation.util.ListItemListener
import com.julkar.mymovie.util.IMAGE_URL_POSTER
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_list_item.view.*

/**
 * @author Julkar Nain
 * since 12/19/20.
 */
class MovieAdapter<Data>(
    private val listener: ListItemListener<Data>,
    private var movieList: List<Movie>
) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder<Data>>() {
    private lateinit var context: Context

    fun update(movieList: List<Movie>) {
        this.movieList = movieList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ViewHolder<Data> {
        val itemView: View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.movie_list_item, parent, false)

        context = parent.context

        return ViewHolder(listener, itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder<Data>, position: Int) {
        val movie = movieList[position]
        bindText(holder.title, movie.title)
        bindText(holder.releaseDate, "Release Date : ${movie.releaseDate}")
        bindText(holder.voteCount, "Vote Count : ${movie.voteCount}")
        holder.container.setTag(R.id.item_position, position)
        holder.container.setTag(R.id.item_data, movie)

        Picasso.get()
            .load(IMAGE_URL_POSTER + movie.posterPath)
            .into(holder.image)
    }

    override fun getItemCount(): Int = movieList.size

    class ViewHolder<Data>(private val listener: ListItemListener<Data>, itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.title
        val releaseDate: TextView = itemView.releaseDate
        val voteCount: TextView = itemView.voteCount
        val image: ImageView = itemView.image
        val container: LinearLayout = itemView.container

        init {

            container.setOnClickListener { view ->
                val position = view.getTag(R.id.item_position) as Int
                val id = view.getTag(R.id.item_data) as Data
                listener.onClick(view, position, id)
            }
        }
    }

    private fun bindText(textView: TextView, value: String) {
        textView.text = value
    }