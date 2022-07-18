package com.example.whistler_mshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.JsonHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONArray

class MainActivity : AppCompatActivity() {
    // declarations
    lateinit var productList:ArrayList<Product>
    lateinit var recyclerAdapter: RecyclerAdapter
    lateinit var recyclerView: RecyclerView
    lateinit var progressBar: ProgressBar



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler)
        progressBar = findViewById(R.id.progressbar)

        progressBar.visibility = View.VISIBLE

        var client = AsyncHttpClient(true, 80, 443)

        recyclerAdapter = RecyclerAdapter(applicationContext)
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.setHasFixedSize(true)

        // LoopJ = Recieve data from the Internet (json), we convert to String


        client.get(this, "https://modcom.pythonanywhere.com/api/all", null, "application/json",

            object : JsonHttpResponseHandler(){

                override fun onSuccess(
                    statusCode: Int,
                    headers: Array<out Header>?,
                    response: JSONArray?
                ) {

                    var gson = GsonBuilder().create()
                    var list = gson.fromJson(response.toString(), Array<Product>::class.java).toList()

                    recyclerAdapter.setProductListItems(list)

                    progressBar.visibility = View.GONE

                    Toast.makeText(applicationContext, "Status Code : "+statusCode, Toast.LENGTH_LONG).show()




                }


            } // end of json http handler



            ) // end client

        recyclerView.adapter = recyclerAdapter







    }

}