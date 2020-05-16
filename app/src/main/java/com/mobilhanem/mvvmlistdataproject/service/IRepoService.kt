package com.mobilhanem.mvvmlistdataproject.service

import com.mobilhanem.mvvmlistdataproject.model.UserReposItem
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface IRepoService {

    @GET("/users/{user}/repos")
    fun fetchUserRepoList(@Path("user") user: String): Observable<ArrayList<UserReposItem>>

    companion object {

        operator fun invoke(): IRepoService {

            return Retrofit.Builder()
                .baseUrl(Const.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(IRepoService::class.java)
        }
    }

}