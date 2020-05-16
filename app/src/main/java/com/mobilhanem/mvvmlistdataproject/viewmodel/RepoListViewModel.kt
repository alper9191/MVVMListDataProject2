package com.mobilhanem.mvvmlistdataproject.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mobilhanem.mvvmlistdataproject.model.ResultApi
import com.mobilhanem.mvvmlistdataproject.model.UserReposItem
import com.mobilhanem.mvvmlistdataproject.repository.RepoListRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class RepoListViewModel : ViewModel() {

    private var repoListRepository: RepoListRepository? = null
    var allRepoList = MutableLiveData<ResultApi>()


    init {
        repoListRepository = RepoListRepository()
    }


    @SuppressLint("CheckResult")
    fun searchRepoList(input: String) {

        repoListRepository?.fetchRepoList(input)?.subscribeOn(Schedulers.newThread())
            ?.observeOn(AndroidSchedulers.mainThread())?.subscribe ({ list ->

            allRepoList?.value = ResultApi(list, null, ResultApi.Status.SUCCESS)

        } ,
        {

            allRepoList?.value = ResultApi(null, it.message, ResultApi.Status.ERROR)

        })

    }

}