package com.mobilhanem.mvvmlistdataproject.repository

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.mobilhanem.mvvmlistdataproject.model.ResultApi
import com.mobilhanem.mvvmlistdataproject.model.UserReposItem
import com.mobilhanem.mvvmlistdataproject.service.IRepoService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*
import kotlin.collections.ArrayList


class RepoListRepository {

    @SuppressLint("CheckResult")
    fun fetchRepoList(userName: String): Observable<ArrayList<UserReposItem>> {

        return IRepoService().fetchUserRepoList(userName)
    }

}