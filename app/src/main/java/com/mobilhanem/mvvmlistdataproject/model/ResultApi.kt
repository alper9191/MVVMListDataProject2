package com.mobilhanem.mvvmlistdataproject.model

class ResultApi(
    val data: ArrayList<UserReposItem>?,
    val message: String?,
    val status: Status
) {

    enum class Status {
        SUCCESS,
        ERROR,
    }
}