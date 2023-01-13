package com.example.shoppingapp.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingapp.News
import com.example.shoppingapp.R
import com.example.shoppingapp.adapters.MainAdapter

class HomeFragment: Fragment(R.layout.fragment_home) {
    private lateinit var adapter: MainAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var newsArrayList: ArrayList<News>

    lateinit var imageId: ArrayList<Int>
    lateinit var heading: ArrayList<String>
    lateinit var news: ArrayList<String>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataInitialize()
        val layoutManager = GridLayoutManager(context,2)
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        adapter = MainAdapter(newsArrayList)
        recyclerView.adapter = adapter

    }
    private fun dataInitialize(){


        newsArrayList = arrayListOf<News>()

        imageId = arrayListOf(
            R.drawable.protein1,
            R.drawable.protein2,
            R.drawable.protein3,
            R.drawable.protein4,
            R.drawable.protein5,
            R.drawable.protein6,
            R.drawable.protein7,
            R.drawable.protein8

        )
        heading = arrayListOf(
            getString(R.string.heading_1),
            getString(R.string.heading_2),
            getString(R.string.heading_3),
            getString(R.string.heading_4),
            getString(R.string.heading_5),
            getString(R.string.heading_6),
            getString(R.string.heading_7),
            getString(R.string.heading_8)


        )
        news = arrayListOf(
            getString(R.string.news_1),
            getString(R.string.news_2),
            getString(R.string.news_3),
            getString(R.string.news_4),
            getString(R.string.news_5),
            getString(R.string.news_6),
            getString(R.string.news_7),
            getString(R.string.news_8)
            )

        for (i in imageId.indices){

            val news =News(imageId[i],heading[i])
            newsArrayList.add(news)
        }
    }

}