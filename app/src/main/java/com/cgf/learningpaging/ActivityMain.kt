package com.cgf.learningpaging;

import android.arch.lifecycle.Observer
import android.arch.paging.LivePagedListBuilder
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.cgf.learningpaging.databinding.ActivityMainBinding

class ActivityMain : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val ding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main);
        val factory = MyFactory()
        val dataList = LivePagedListBuilder(factory, Constants.pageSize).build()
        val adapter = MyAdapter()
        ding?.recyclerView?.adapter = adapter
        ding.swipeRefreshLayout.setOnRefreshListener {
            factory.dataSource.invalidate()
        }
        dataList.observe(this, Observer {
            adapter.submitList(it)
        })
        ding?.recyclerView?.layoutManager = LinearLayoutManager(this)
    }
}
