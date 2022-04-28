package com.yerayyas.mvvm.ui.single_movie_details

import androidx.lifecycle.LiveData
import com.yerayyas.mvvm.data.api.TheMovieDBInterface
import com.yerayyas.mvvm.data.repository.MovieDetailsNetworkDataSource
import com.yerayyas.mvvm.data.repository.NetworkState
import com.yerayyas.mvvm.data.vo.MovieDetails
import io.reactivex.disposables.CompositeDisposable

class MovieDetailsRepository(private val apiService: TheMovieDBInterface) {

    lateinit var movieDetailsNetworkDataSource: MovieDetailsNetworkDataSource

    fun fetchSingleMovieDetails(compositeDisposable: CompositeDisposable,movieId:Int):LiveData<MovieDetails>{

        movieDetailsNetworkDataSource= MovieDetailsNetworkDataSource(apiService,compositeDisposable)
        movieDetailsNetworkDataSource.fetchMovieDetails(movieId)

        return movieDetailsNetworkDataSource.downloadedMoviewDetailsResponse
    }

    fun getMovieDetailsNetworkState():LiveData<NetworkState>{
        return movieDetailsNetworkDataSource.networkState
    }
}