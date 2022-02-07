package ru.gb.dictionary.view.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.gb.dictionary.R
import ru.gb.dictionary.model.data.DataModel


class MainAdapter(
    private var onListItemClickListener: OnListItemClickListener,
    private var data: List<DataModel>
): RecyclerView.Adapter<MainAdapter.RecyclerItemViewHolder>() {


    fun setData(data: List<DataModel>){
        this.data = data
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
       return RecyclerItemViewHolder(
           LayoutInflater.from(parent.context).inflate(R.layout.main_rv_item,parent,false)
       )
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }


    inner class RecyclerItemViewHolder(view: View) : RecyclerView.ViewHolder(view){

        val headerTextView = itemView.findViewById<TextView>(R.id.header_textview_recycler_item)
        val descriptionTextView = itemView.findViewById<TextView>(R.id.description_textview_recycler_item)

        fun bind (data: DataModel){
            if(layoutPosition != RecyclerView.NO_POSITION){
                headerTextView.text = data.text
                descriptionTextView.text =
                    data.meanings?.get(0)?.translation?.translation
                itemView.setOnClickListener { onListItemClickListener.onItemClick(data) }

            }
        }

    }




   fun interface OnListItemClickListener{
        fun onItemClick(data: DataModel)
    }

}