package com.example.networking

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

 class MyAdapter(val context: Context, val userList: List<MyDataItem>)
    : RecyclerView.Adapter<MyAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
       var userId:TextView
       var userTitle:TextView

        init{
           userId=itemView.findViewById(R.id.userId)
            userTitle=itemView.findViewById(R.id.userTitle)
       }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       var itemView=LayoutInflater.from(parent.context).inflate(R.layout.row_items,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.userId.text = userList[position].userId.toString()
        holder.userTitle.text=userList[position].title
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}