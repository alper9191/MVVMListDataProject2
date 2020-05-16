package com.mobilhanem.mvvmlistdataproject

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.mobilhanem.mvvmlistdataproject.model.UserReposItem


class DetailRepoActivity : AppCompatActivity() {

    private var userRepo: UserReposItem? = null
    private val userRepoStarCount by lazy { findViewById<TextView>(R.id.userRepoStarCount) }
    private val userRepoOpenIssueCount by lazy { findViewById<TextView>(R.id.userRepoOpenIssueCount) }
    private val userName by lazy { findViewById<TextView>(R.id.userName) }
    private val userAvatar by lazy { findViewById<ImageView>(R.id.userAvatar) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_repo)

        userRepo = intent.getSerializableExtra("EXTRA_REPO") as? UserReposItem

        initToolbar()

        userName.text = userRepo?.owner?.login
        userRepoOpenIssueCount.text = userRepo?.openIssuesCount.toString()
        userRepoStarCount.text = userRepo?.stargazersCount.toString()
        Glide.with(this)
            .load(userRepo?.owner?.avatarUrl)
            .override(300, 300)
            .into(userAvatar)
    }


    private fun initToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar).apply {
            setSupportActionBar(this)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = userRepo?.name
        }

        toolbar.setNavigationOnClickListener { finish() }

    }

}
