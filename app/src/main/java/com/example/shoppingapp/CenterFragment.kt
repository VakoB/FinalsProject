package com.example.shoppingapp

import android.os.Bundle
import android.view.View
import android.widget.Toast
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

class CenterFragment: Fragment(R.layout.fragment_center) {
    private lateinit var viewPager2: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPagerFragmentAdapter: ViewPagerFragmentAdapter
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
        price = arrayListOf(
            100,
            200,
            150,
            300,
            120,
            199,
            149,
            229
        )


        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        newsArrayList = arrayListOf<News>()

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
                R.id.cartFragment -> {
                    Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
                        .navigate(R.id.action_centerFragment_to_cartFragment)
                    true
                }
                else -> false


            }
        }
    }

    private fun getUserData() {
        for (i in imageId.indices) {
            val news = News(imageId[i], heading[i], price[i])
            newsArrayList.add(news)
        }
        val adapter = MainAdapter(newsArrayList)
        recyclerView.adapter = adapter
        adapter.setOnItemClickListener(object : MainAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                Toast.makeText(requireContext(),"Item Number:$position",Toast.LENGTH_SHORT).show()
                val bundle = Bundle()
                /*bundle.putString("heading",newsArrayList[position].heading)
                bundle.putString("news",news[position])
                bundle.putInt("imageId", newsArrayList[position].Image)
                bundle.putInt("prices",price[position])
                bundle.putSerializable("key", newsArrayList)*/

                val secondFragment = CartFragment()
                secondFragment.arguments = bundle


            }


        })
    }


}