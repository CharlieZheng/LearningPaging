package com.cgf.learningpaging

import android.arch.paging.DataSource


class MyFactory : DataSource.Factory<String, Entity>() {
    val dataSource = MyDataSource()
    override fun create(): DataSource<String, Entity> {
        return dataSource
    }
}