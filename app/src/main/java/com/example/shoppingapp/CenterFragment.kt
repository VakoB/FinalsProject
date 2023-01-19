package com.example.shoppingapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.shoppingapp.adapters.MainAdapter
import com.example.shoppingapp.adapters.ViewPagerFragmentAdapter
import com.example.shoppingapp.fragments.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.FirebaseAuth

class CenterFragment: Fragment(R.layout.fragment_center) {
    private lateinit var adapter: MainAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var newsArrayList: ArrayList<News>
    private val sharedViewModel: SharedViewModel by activityViewModels()

    lateinit var imageId: ArrayList<Int>
    lateinit var heading: ArrayList<String>
    lateinit var news: ArrayList<String>
    lateinit var price: ArrayList<Int>

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        imageId = arrayListOf(
            R.drawable.protein1,
            R.drawable.protein2,
            R.drawable.protein3,
            R.drawable.protein4,
            R.drawable.protein5,
            R.drawable.protein6,
            R.drawable.protein7,
            R.drawable.protein8,
            R.drawable.protein9,
            R.drawable.protein10,
            R.drawable.protein11,
            R.drawable.protein12,
            R.drawable.protein13,
            R.drawable.protein14,
            R.drawable.protein15,
            R.drawable.protein16,
            R.drawable.protein17,
            R.drawable.protein18,
            R.drawable.protein19,
            R.drawable.protein20,
            R.drawable.protein21,
            R.drawable.protein22,
            R.drawable.protein23,
            R.drawable.protein24,
            R.drawable.protein25,
            R.drawable.protein26,
            R.drawable.protein27




        )

        heading = arrayListOf(
            getString(R.string.heading_1),
            getString(R.string.heading_2),
            getString(R.string.heading_3),
            getString(R.string.heading_4),
            getString(R.string.heading_5),
            getString(R.string.heading_6),
            getString(R.string.heading_7),
            getString(R.string.heading_8),
            getString(R.string.heading_9),
            getString(R.string.heading_10),
            getString(R.string.heading_11),
            getString(R.string.heading_12),
            getString(R.string.heading_13),
            getString(R.string.heading_14),
            getString(R.string.heading_15),
            getString(R.string.heading_16),
            getString(R.string.heading_17),
            getString(R.string.heading_18),
            getString(R.string.heading_19),
            getString(R.string.heading_20),
            getString(R.string.heading_21),
            getString(R.string.heading_22),
            getString(R.string.heading_23),
            getString(R.string.heading_24),
            getString(R.string.heading_25),
            getString(R.string.heading_26),
            getString(R.string.heading_27)


        )

        news = arrayListOf(
            getString(R.string.news_1),
            getString(R.string.news_2),
            getString(R.string.news_3),
            getString(R.string.news_4),
            getString(R.string.news_5),
            getString(R.string.news_6),
            getString(R.string.news_7),
            getString(R.string.news_8),
            getString(R.string.news_9),
            getString(R.string.news_10),
            getString(R.string.news_11),
            getString(R.string.news_12),
            getString(R.string.news_13),
            getString(R.string.news_14),
            getString(R.string.news_15),
            getString(R.string.news_16),
            getString(R.string.news_17),
            getString(R.string.news_18),
            getString(R.string.news_19),
            getString(R.string.news_20),
            getString(R.string.news_21),
            getString(R.string.news_22),
            getString(R.string.news_23),
            getString(R.string.news_24),
            getString(R.string.news_25),
            getString(R.string.news_26),
            getString(R.string.news_27),
        )

        price = arrayListOf(
            10,
            20,
            15,
            30,
            12,
            19,
            49,
            29,
            23,
            42,
            22,
            42,
            35,
            25,
            23,
            35,
            22,
            53,
            32,
            63,
            36,
            26,
            72,
            21,
            24,
            51,
            43
        )


        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        newsArrayList = arrayListOf()

        adapter = MainAdapter(newsArrayList)

        getUserData()


        bottomNavigationView = view.findViewById(R.id.bottom_navigation_view)
        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.centerFragment -> {
                    Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
                        .navigate(R.id.action_centerFragment_self)
                    true
                }
                R.id.listFragment -> {
                    Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
                        .navigate(R.id.action_centerFragment_to_listFragment)

                    true
                }
                R.id.aboutAppFragment -> {
                    Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
                        .navigate(R.id.action_centerFragment_to_aboutAppFragment)
                    true
                }
                R.id.profileFragment -> {
                    Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
                        .navigate(R.id.action_centerFragment_to_profileFragment)
                    true
                }
                else -> false


            }
        }
    }

    private fun getUserData() {
        for (i in imageId.indices) {
            val news = News(imageId[i], heading[i], price[i], news[i])
            newsArrayList.add(news)
        }
        val adapter = MainAdapter(newsArrayList)
        recyclerView.adapter = adapter
        adapter.setOnItemClickListener(object : MainAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {

                val builder = AlertDialog.Builder(requireContext())
                val dialogView = layoutInflater.inflate(R.layout.dialog_layout, null)
                val heading = dialogView.findViewById<TextView>(R.id.heading)
                val imageView = dialogView.findViewById<ImageView>(R.id.imageView)
                val buttonConfirm = dialogView.findViewById<Button>(R.id.btn_confirm)
                val buttonDeny = dialogView.findViewById<Button>(R.id.btn_cancel)
                imageView.setImageResource(newsArrayList[position].Image)
                if(heading!=null)
                    heading.setText(news[position])

                builder.setView(dialogView)
                val dialog = builder.create()
                buttonConfirm.setOnClickListener {
                    Toast.makeText(requireContext(),"თქვენ შეიძინეთ ${news[position]}!",Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }
                buttonDeny.setOnClickListener {
                    dialog.cancel()
                }


                dialog.show()


            }


        })
    }



}