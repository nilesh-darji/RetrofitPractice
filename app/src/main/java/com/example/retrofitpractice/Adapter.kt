package com.example.retrofitpractice

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(private var userList: ArrayList<UserModel>, private val context: Context) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text=itemView.findViewById<TextView>(R.id.raw_tv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.raw_layout, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val list=userList[position]
        holder.text.text=list.first_name+" "+list.last_name
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}