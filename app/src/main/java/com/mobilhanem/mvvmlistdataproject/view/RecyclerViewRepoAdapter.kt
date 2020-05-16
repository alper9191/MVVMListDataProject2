package com.mobilhanem.mvvmlistdataproject.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mobilhanem.mvvmlistdataproject.R
import com.mobilhanem.mvvmlistdataproject.model.UserReposItem

class RecyclerViewRepoAdapter(
    private var context: Context,
    private var dataList: ArrayList<UserReposItem>,
    private val click: (userRepo: UserReposItem) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val EMPTY_VIEW = 0
        const val REPO_ITEM_VIEW = 1
    }

    override fun getItemCount() = if (dataList.isEmpty()) 1 else dataList.size

    override fun getItemViewType(position: Int) =
        if (dataList.isEmpty()) EMPTY_VIEW else REPO_ITEM_VIEW

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {

            EMPTY_VIEW -> {

                val v = LayoutInflater.from(context).inflate(
                    R.layout.empty_item_view,
                    parent,
                    false
                )
                EmptyViewHolder(v)

            }
            else -> {

                val v = LayoutInflater.from(context).inflate(
                    R.layout.repo_item_view,
                    parent,
                    false
                )
                RepoItemViewHolder(v)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {

            is RepoItemViewHolder -> holder.bindItems(dataList[position])

        }

    }

    fun updateAdapter(updateList: ArrayList<UserReposItem>) {
        dataList.clear()
        dataList.addAll(updateList)
        notifyDataSetChanged()
    }

    fun clear() {
        dataList.clear()
        notifyDataSetChanged()
    }

    inner class RepoItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var textViewName: TextView = itemView.findViewById(R.id.repoName)
        fun bindItems(userRepo: UserReposItem) {
            textViewName.text = userRepo.name

            itemView.setOnClickListener(View.OnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    click(userRepo)
                }
            })
        }
    }

    inner class EmptyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }


}