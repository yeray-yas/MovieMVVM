package com.yerayyas.mvvm.ui.single_movie_details

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.yerayyas.mvvm.data.api.POSTER_BASE_URL
import com.yerayyas.mvvm.data.api.TheMovieDBClient
import com.yerayyas.mvvm.data.api.TheMovieDBInterface
import com.yerayyas.mvvm.data.repository.NetworkState
import com.yerayyas.mvvm.data.vo.MovieDetails
import kotlinx.android.synthetic.main.activity_single.*
import com.yerayyas.mvvm.R

class SingleActivity : AppCompatActivity() {

    private lateinit var viewModel: SingleMovieViewModel
    private  lateinit var movieRepository: MovieDetailsRepository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single)

        val movieId:Int=intent.getIntExtra("id",1)
        println("id $movieId")

        val apiService: TheMovieDBInterface = TheMovieDBClient.getClient()
        movieRepository= MovieDetailsRepository(apiService)

        viewModel=getViewModel(movieId)

        viewModel.movieDetails.observe(this, Observer {
            bindUI(it)
        })

        viewModel.networkState.observe(this, Observer {
            progress_bar.visibility=if (it== NetworkState.LOADING)View.VISIBLE else View.GONE
            txt_error.visibility=if (it== NetworkState.ERROR)View.VISIBLE else View.GONE
        })

    }

    @SuppressLint("SetTextI18n")
    private fun bindUI(it: MovieDetails?) {
        if (it != null) {
            movie_title.text = it.title

            movie_rating.text = it.rating.toString()

            movie_overview.text = it.overview


            val moviePosterURL = POSTER_BASE_URL + it.posterPath
            Glide.with(this)
                .load(moviePosterURL)
                .into(iv_movie_poster)
        }

    }


    private fun getViewModel(movieId:Int) : SingleMovieViewModel {
        return ViewModelProvider(this, object :ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return SingleMovieViewModel(movieRepository,movieId) as T
            }

        })[SingleMovieViewModel::class.java]
    }

}
