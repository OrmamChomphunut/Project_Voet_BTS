package com.example.facebook


import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.view.*
import org.json.JSONArray
import java.lang.StringBuilder


class CustomAdapter(context: Context, Fm:FragmentManager,val dataSource: JSONArray) : RecyclerView.Adapter<CustomAdapter.Holder>() {

    private val thiscontext : Context = context
    private val fm: FragmentManager = Fm
    var description :String ?= null
    var title :String ?= null
    var image :String ?= null

    class Holder(view : View) : RecyclerView.ViewHolder(view) {
        private val View = view;

        lateinit var layout : LinearLayout
        lateinit var titleTextView: TextView
        lateinit var detailTextView: TextView
        lateinit var image: ImageView

        fun Holder(){

            layout = View.findViewById<View>(R.id.recyview_layout) as LinearLayout
            titleTextView = View.findViewById<View>(R.id.tv_name) as TextView
            detailTextView = View.findViewById<View>(R.id.tv_description) as TextView
            image = View.findViewById<View>(R.id.imgV) as ImageView

        }

    }

    override fun onCreateViewHolder(parent : ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.listview, parent, false))
    }


    override fun getItemCount(): Int {
        return dataSource.length()
    }


    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.Holder()

        holder.titleTextView.setText( dataSource.getJSONObject(position).getString("title").toString() )
        holder.detailTextView.setText( dataSource.getJSONObject(position).getString("description").toString() )

        Glide.with(thiscontext)
            .load(dataSource.getJSONObject(position).getString("image").toString())
            .into(holder.image)

        holder.layout.setOnClickListener{
            Toast.makeText(thiscontext,holder.titleTextView.text.toString(),Toast.LENGTH_SHORT).show()
            description  = dataSource.getJSONObject(position).getString("description").toString()
            title  = dataSource.getJSONObject(position).getString("title").toString()
            image  = dataSource.getJSONObject(position).getString("image").toString()

            val detail = fragment_detail().newInstance(image!!, title!!, description!!)
            val transaction: FragmentTransaction = fm!!.beginTransaction()
            transaction.replace(com.example.facebook.R.id.contentContainer,detail,"detail")
            transaction.addToBackStack("detail")
            transaction.commit()

        }

    }



}



