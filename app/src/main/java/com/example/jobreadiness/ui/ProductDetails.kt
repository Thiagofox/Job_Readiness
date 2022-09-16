package com.example.jobreadiness.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jobreadiness.databinding.ActivityProductDetailsBinding

class ProductDetails : AppCompatActivity() {

    private lateinit var  binding: ActivityProductDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val title = intent.getStringExtra("titlle")
        binding.textProductName.text = title
//        val image = intent.getStringExtra("image")
//        binding.imageProduct.imageAlpha = image
    }
}