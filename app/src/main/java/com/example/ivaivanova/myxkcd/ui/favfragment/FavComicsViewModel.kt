package com.example.ivaivanova.myxkcd.ui.favfragment

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.example.ivaivanova.myxkcd.data.XkcdRepository
import com.example.ivaivanova.myxkcd.model.Comic

/**
 * ViewModel for the Favorite Comics
 */
class FavComicsViewModel(repository: XkcdRepository) : ViewModel() {

    var favComicsResult: LiveData<PagedList<Comic>>

    init {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setPrefetchDistance(10)
            .setPageSize(10)
            .build()

        favComicsResult = LivePagedListBuilder(repository.getAllFavs(), config).build()
    }


    fun getFavComics(): LiveData<PagedList<Comic>> {
        return favComicsResult
    }


}