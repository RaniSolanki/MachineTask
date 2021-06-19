package com.`in`.machinetask

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.`in`.machinetask.databinding.ActivityMainBinding
import java.util.*


class MainActivity : AppCompatActivity() {
    var listViewModel: ListViewModel? = null
    lateinit var binding: ActivityMainBinding
    lateinit var listViewModelArrayList: ArrayList<ListViewModel>
    var adapter: ListAdapter? = null
    lateinit var gridLayoutManager: GridLayoutManager

    private val PAGE_START = 0
    private val isLoading = false
    private val isLastPage = false
    private val TOTAL_PAGES = 3
    private var currentPage = PAGE_START


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Specify the current activity as the lifecycle owner.
        binding.setLifecycleOwner(this)
        initView()
    }



    private fun initView() {

        listViewModel = ListViewModel(this@MainActivity)

        binding.executePendingBindings()
        binding.listVM = listViewModel
        binding.recyclerview.visibility = View.GONE
        binding.shimmerView.visibility = View.VISIBLE
        binding.recyclerview.setItemAnimator(DefaultItemAnimator())
        binding.shimmerView.startShimmerAnimation()

        listViewModel!!.getList(34, this)


        binding.layChangetheme.setOnClickListener(View.OnClickListener {
            if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            finish()
            startActivity(Intent(this@MainActivity, this@MainActivity.javaClass))
        })

        listViewModel!!.arrayListMutableLiveData.observe(this, Observer { it ->
            listViewModelArrayList = it as ArrayList<ListViewModel>


            gridLayoutManager = GridLayoutManager(this@MainActivity, 2)
            binding.recyclerview.layoutManager = GridLayoutManager(this@MainActivity, 2)
            binding.recyclerview.visibility = View.VISIBLE
            binding.shimmerView.stopShimmerAnimation()
            binding.shimmerView.visibility = View.GONE
            adapter = ListAdapter(
                listViewModelArrayList,
                this@MainActivity
            )
            binding.listAdapter = adapter
            binding.executePendingBindings()
        })


    }


}