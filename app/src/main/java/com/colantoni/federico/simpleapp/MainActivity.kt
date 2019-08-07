package com.colantoni.federico.simpleapp


import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.colantoni.federico.simpleapp.repository.MangaViewModel


class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MangaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(MangaViewModel::class.java)
    }

    override fun onStart() {

        super.onStart()

        viewModel.firstTodo.observe(this, Observer {
            Toast.makeText(this@MainActivity, "Total manga: " + it.total, Toast.LENGTH_LONG).show()
        })
    }

    companion object {

        const val BASE_URL = "http://www.mangaeden.com/api/"
    }
}
