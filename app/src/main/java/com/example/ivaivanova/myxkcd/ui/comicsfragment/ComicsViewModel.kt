package com.example.ivaivanova.myxkcd.ui.comicsfragment

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations.switchMap
import android.arch.lifecycle.ViewModel
import android.arch.paging.DataSource
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.example.ivaivanova.myxkcd.model.Comic
import com.example.ivaivanova.myxkcd.utils.NetworkState

/**
 * ViewModel for the Comics class
 * NT: Do not hold an instance of the context here!!!
 */
class ComicsViewModel: ViewModel() {

    private val comicName: MutableLiveData<String> = MutableLiveData()

    var comicsResult: LiveData<PagedList<Comic>>

    lateinit var networkState: LiveData<NetworkState>
    lateinit var loadingState: LiveData<NetworkState>


    init {
        // TODO: Question: I have no idea what does switchMap do!!!
        // TODO: Q -
        networkState = switchMap(comicName) { networkState }
        loadingState = switchMap(comicName) { loadingState }

        // COMPLETED: Move this in the ViewModel later
        val config = PagedList.Config.Builder()
            .setPageSize(10)
            .setEnablePlaceholders(false)
            .build()

        comicsResult = initializedPagedListBuilder(config).build()
    }


    private fun initializedPagedListBuilder(config: PagedList.Config) :
            LivePagedListBuilder<Int, Comic> {

        val dataSourceFactory = object : DataSource.Factory<Int, Comic>() {
            override fun create(): DataSource<Int, Comic> {
                return XkcdDataSource()
            }
        }

        return LivePagedListBuilder<Int, Comic>(dataSourceFactory, config)
    }

    fun refreshData() {
        val config = PagedList.Config.Builder()
            .setPageSize(10)
            .setEnablePlaceholders(false)
            .build()

        comicsResult = initializedPagedListBuilder(config).build()
    }

}