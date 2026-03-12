package com.uilover.project239.Adapter
import android.content.Context
import android.view.ViewGroup
import com.uilover.project239.Domain.ItemsModel
import androidx.recyclerview.widget.RecyclerView
import com.uilover.project239.databinding.ViewholderPopularBinding
import android.view.LayoutInflater
import com.bumptech.glide.Glide
import com.uilover.project239.Activity.DetailActivity
import android.content.Intent
class PopularAdapter(val items: MutableList<ItemsModel>) : RecyclerView.Adapter<PopularAdapter.ViewHolder>() {
    lateinit var context: Context
    class ViewHolder(val binding: ViewholderPopularBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PopularAdapter.ViewHolder {
        context=parent.context
        val binding= ViewholderPopularBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PopularAdapter.ViewHolder, position: Int) {
        holder.binding.titleTxt.text=items[position].title
        holder.binding.priceTxt.text="$"+items[position].price.toString()
        holder.binding.subtitleTxt.text=items[position].extra.toString()
        Glide.with(context)
            .load(items[position].picUrl[0])
            .into(holder.binding.pic)
        holder.itemView.setOnClickListener {
            val intent= Intent(context, DetailActivity::class.java)
            intent.putExtra("object",items[position])
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int =items.size

}