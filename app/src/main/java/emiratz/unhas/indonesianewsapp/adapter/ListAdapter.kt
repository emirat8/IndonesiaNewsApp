package emiratz.unhas.indonesianewsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import emiratz.unhas.indonesianewsapp.R
import emiratz.unhas.indonesianewsapp.databinding.ItemNewsBinding
import emiratz.unhas.indonesianewsapp.model.News
import emiratz.unhas.indonesianewsapp.utils.DataMapper

class ListAdapter : RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    private lateinit var onItemClickListener: OnItemClickListener
    private var listData = ArrayList<News>()

    fun setData(data: ArrayList<News>) {
        this.listData.clear()
        this.listData.addAll(data)
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListAdapter.ListViewHolder {
        val binding =
            ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListAdapter.ListViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int = listData.size

    inner class ListViewHolder(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: News) {
            with(binding) {
                tvTitleNews.text = item.title
                tvDateNews.text = DataMapper.newsDateFormatter(item.date)

                Glide.with(itemView.context)
                    .load(item.image)
                    .centerCrop()
                    .override(600, 600)
                    .placeholder(R.drawable.image_load)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imgItemNews)

                itemView.setOnClickListener {
                    onItemClickListener.onNewsClicked(item)
                }
            }
        }
    }

    interface OnItemClickListener {
        fun onNewsClicked(news: News)
    }
}