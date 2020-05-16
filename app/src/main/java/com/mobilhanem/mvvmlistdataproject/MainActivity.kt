package com.mobilhanem.mvvmlistdataproject

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.mobilhanem.mvvmlistdataproject.model.ResultApi
import com.mobilhanem.mvvmlistdataproject.model.UserReposItem
import com.mobilhanem.mvvmlistdataproject.view.RecyclerViewRepoAdapter
import com.mobilhanem.mvvmlistdataproject.viewmodel.RepoListViewModel


class MainActivity : AppCompatActivity() {

    private val recyclerview by lazy { findViewById<RecyclerView>(R.id.repoListRecyclerView) }
    private var recyclerViewRepoAdapter: RecyclerViewRepoAdapter? = null

    private val submitButton by lazy { findViewById<Button>(R.id.submitButton) }
    private val searchTextInputEditText by lazy { findViewById<TextInputEditText>(R.id.searchTextInputEditText) }
    private lateinit var viewModel: RepoListViewModel

    var dataList: ArrayList<UserReposItem> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = getString(R.string.home)

        recyclerViewRepoAdapter = RecyclerViewRepoAdapter(this, dataList) { userRepo ->

            val detailIntent = Intent(this@MainActivity, DetailRepoActivity::class.java);
            detailIntent.putExtra("EXTRA_REPO", userRepo)
            startActivity(detailIntent)
        }

        recyclerview.apply {

            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = recyclerViewRepoAdapter

        }

        viewModel = ViewModelProviders.of(this).get(RepoListViewModel::class.java)

        viewModel.allRepoList.observe(this, Observer { response ->

            if (response.status == ResultApi.Status.SUCCESS) {
                response.data?.let {
                    recyclerViewRepoAdapter?.updateAdapter(response.data)
                }
            } else if (response.message != null && response.status == ResultApi.Status.ERROR) {
                recyclerViewRepoAdapter?.clear()
                Toast.makeText(this, response.message, Toast.LENGTH_LONG).show()
            }

        })

        submitButton.setOnClickListener(View.OnClickListener {

            val userInput: String = searchTextInputEditText.text.toString()
            if (userInput.isNotEmpty()) {

                viewModel.searchRepoList(userInput)

            } else {
                Toast.makeText(this, getString(R.string.empty_input_warning), Toast.LENGTH_LONG).show()
            }

        })

    }
}
