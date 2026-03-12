package com.uilover.project239.Activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.uilover.project239.R
import com.uilover.project239.databinding.ActivityCartBinding
import com.uilover.project239.Helper.ManagmentCart
import com.uilover.project239.Helper.ChangeNumberItemsListener
import com.uilover.project239.Adapter.CartAdapter

class CartActivity : AppCompatActivity() {
    lateinit var binding: ActivityCartBinding
    private var managmentCart=ManagmentCart(this)
    private var tax: Double=0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cart)
        managmentCart= ManagmentCart(this)
        calculateCart()
        setVariable()
        initCartList()
    }

    private fun initCartList() {
        binding.apply{
            listView.layoutManager= LinearLayoutManager(this@CartActivity,LinearLayoutManager.VERTICAL,false)
            listView.adapter=CartAdapter(managmentCart.getListCart(),this@CartActivity,object :ChangeNumberItemsListener{
                override fun onChanged() {
                    calculateCart()
                }

            })
        }
    }

    private fun setVariable(){
        binding.backBtn.setOnClickListener {
            finish()
        }

    }

    private fun calculateCart() {
        val percentTax= 0.02
        val delivery=15
        tax=Math.round((managmentCart.getTotalFee()*percentTax)*100)/100.0
        val total=Math.round((managmentCart.getTotalFee()+tax+delivery)*100)/100
        val itemTotal=Math.round(managmentCart.getTotalFee()*100)/100
        binding.apply {
            totalFeeTxt.text="$$itemTotal"
            deliveryTxt.text="$$delivery"
            totalTaxTxt.text="$$tax"
            totalTxt.text="$$total"
        }


    }
}