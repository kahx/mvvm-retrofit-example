package com.kaansa.nuevo.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kaansa.nuevo.R
import com.kaansa.nuevo.models.Photo
import com.kaansa.nuevo.models.PhotoItem
import com.kaansa.nuevo.ui.Recycler.OnPhotoClickListener
import com.kaansa.nuevo.ui.Recycler.RecyclerAdapter
import com.kaansa.nuevo.viewmodels.PhotoViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), OnPhotoClickListener {

    private lateinit var viewModel : PhotoViewModel
    private lateinit var photoAdapter : RecyclerAdapter
    private lateinit var photoData : Photo
    private lateinit var coverImage : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(PhotoViewModel::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            viewModel.getData()
        }

        viewModel.photos.observe(this, Observer { photoList ->
            photoData = photoList
            coverImage = findViewById(R.id.coverImage)
            Picasso.get().load(photoList[0].url)
                .into(coverImage)
            photoAdapter = RecyclerAdapter(photoList, this)
            rv_photo_list.layoutManager = LinearLayoutManager(this)
            rv_photo_list.adapter = photoAdapter
            photoAdapter.notifyDataSetChanged()

        })



    }

    override fun onPhotoItemClicked(position: Int) {
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra("id", photoData[position].id.toString())
        intent.putExtra("image", photoData[position].url)
        intent.putExtra("title", photoData[position].title)
        startActivity(intent)
    }
}