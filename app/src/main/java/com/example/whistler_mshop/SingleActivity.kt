package com.example.whistler_mshop

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class SingleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single)

        val prefs: SharedPreferences = getSharedPreferences("store",
            Context.MODE_PRIVATE)

        val title = prefs.getString("Product Name", "")
        val text_title = findViewById(R.id.p_name) as TextView
        text_title.text = title

        val desc = prefs.getString("description", "")
        val text_desc = findViewById(R.id.p_desc) as TextView
        text_desc.text = desc

        val cost = prefs.getString("product_cost", "")
        val text_cost= findViewById(R.id.p_cost) as TextView
        text_cost.text = cost

        val image_url = prefs.getString("image_url", "")
        val image = findViewById(R.id.img_url) as ImageView
        Glide.with(applicationContext).load(image_url)
            .apply(RequestOptions().centerCrop())
            .into(image)






    }
}