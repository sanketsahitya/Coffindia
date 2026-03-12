package com.uilover.project239.Adapter
import android.content.Context
import android.content.Intent
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.Transaction
import androidx.core.os.postDelayed
import com.uilover.project239.Domain.CategoryModel
import com.uilover.project239.databinding.ViewholderCategoryBinding
import android.os.Handler
import androidx.core.content.ContextCompat
import com.uilover.project239.R
import com.uilover.project239.Activity.ItemsListActivity

//import android.view.ViewGroup
//import android.view.LayoutInflater
//import com.uilover.project239.R
//import android.widget.TextView

class CategoryAdapter(val items: MutableList<CategoryModel>) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    private lateinit var context: Context
    private var selectedposition=-1
    private var lastSelectedPosition=-1
    inner class ViewHolder(val binding: ViewholderCategoryBinding):RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryAdapter.ViewHolder {
        context=parent.context
        val binding= ViewholderCategoryBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryAdapter.ViewHolder, position: Int) {
        val item=items[position]
        holder.binding.titleCat.text=item.title
        holder.binding.root.setOnClickListener {
            lastSelectedPosition=selectedposition
            selectedposition=position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedposition)
            Handler(Looper.getMainLooper()).postDelayed({
                val intent= Intent(context, ItemsListActivity::class.java).apply{
                    putExtra("id",item.id.toString())
                    putExtra("title",item.title)

                }
                ContextCompat.startActivity(context,intent,null)

            },500)
        }
        if(selectedposition==position){
            holder.binding.titleCat.setBackgroundResource(R.drawable.grey_full_corner_bg)
            holder.binding.titleCat.setTextColor(context.resources.getColor(R.color.white))
        }else{
            holder.binding.titleCat.setBackgroundResource(R.drawable.white_full_corner)
            holder.binding.titleCat.setTextColor(context.resources.getColor(R.color.darkBrown))
        }
    }

    override fun getItemCount(): Int =items.size

}
