package com.kaansa.nuevo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kaansa.nuevo.R
import com.kaansa.nuevo.ui.Recycler.RecyclerAdapter
import com.kaansa.nuevo.viewmodels.CommentViewModel
import com.kaansa.nuevo.viewmodels.PhotoViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_second.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SecondActivity : AppCompatActivity() {

    private lateinit var viewModel : CommentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val id = intent.getStringExtra("id")
        val url = intent.getStringExtra("image")
        val title = intent.getStringExtra("title")
        var photo_image = findViewById<ImageView>(R.id.photo_image)
        Picasso.get().load(url).into(photo_image)

        viewModel = ViewModelProvider(this).get(CommentViewModel::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            viewModel.getData(id?.toInt()!!)
        }

        viewModel.comments.observe(this, Observer { commentList ->
            photo_body.text = commentList[0].body
            photo_title.text = title
        })

    }
}