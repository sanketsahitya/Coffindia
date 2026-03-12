package com.uilover.project239.Activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.uilover.project239.R
import com.uilover.project239.ViewModel.MainViewModel
import com.uilover.project239.databinding.ActivityItemsListBinding
import android.view.View
import androidx.lifecycle.Observer
import com.uilover.project239.Adapter.ItemListCategoryAdapter
import androidx.recyclerview.widget.GridLayoutManager


class ItemsListActivity : AppCompatActivity() {
    lateinit var binding: ActivityItemsListBinding
    private val viewModel= MainViewModel()
    private var id:String=""
    private var title:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityItemsListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getBundles()
        initList()
    }

    private fun initList() {
        binding.apply {
            progressBar3.visibility=View.VISIBLE
            viewModel.loadCategory(id).observe(this@ItemsListActivity, Observer {
                listView.layoutManager = GridLayoutManager(this@ItemsListActivity, 2)
                listView.adapter = ItemListCategoryAdapter(it)
                progressBar3.visibility = View.GONE
            })
            backBtn.setOnClickListener {
                finish()
            }
        }
    }

    private fun getBundles() {
        id=intent.getStringExtra("id")!!
        title=intent.getStringExtra("title")!!
        binding.categoryTxt.text=title
    }
}