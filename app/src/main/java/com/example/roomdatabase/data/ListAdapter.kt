package com.example.roomdatabase.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabase.R
import kotlinx.android.synthetic.main.custom_item.view.*

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyAdapter>() {
    private lateinit var  listUser : List<User>
    class MyAdapter(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter {
        return MyAdapter(LayoutInflater.from(parent.context).inflate(R.layout.custom_item,parent,false))
    }

    override fun getItemCount(): Int {
        return listUser.size
    }

    override fun onBindViewHolder(holder: MyAdapter, position: Int) {
        var urret = listUser[position]
        holder.itemView.txt_id.text = urret.id.toString()
        holder.itemView.txt_name.text = urret.name
        holder.itemView.txt_address.text = urret.address

        holder.itemView.item_row.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdatefragment(urret)
            holder.itemView.findNavController().navigate(action)
        }

    }
    fun setData(list: List<User>){
        this.listUser = list
        notifyDataSetChanged()
    }
}