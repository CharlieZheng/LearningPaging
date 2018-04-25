package com.cgf.learningpaging

import android.arch.paging.PageKeyedDataSource
import retrofit2.Response
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MyDataSource : PageKeyedDataSource<String, Entity>() {
    private val webservice = UtilRetrofit.getInstance()?.createRetrofit()
    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<String, Entity>) {
        webservice?.getProvinceList(Constants.JU_HE_KEY)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(object : Subscriber<Response<ResApi<List<Entity>>>>() {
                    override fun onNext(t: Response<ResApi<List<Entity>>>?) {
                        callback.onResult(t?.body()?.result ?: emptyList(), "", "")
                    }

                    override fun onCompleted() {
                    }

                    override fun onError(e: Throwable?) {
                    }
                })
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, Entity>) {
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, Entity>) {

    }
}