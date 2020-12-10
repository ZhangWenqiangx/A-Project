package com.example.module_home.data.source

import com.example.common_base.base.BaseDataOperate
import com.example.common_base.base.BaseResult
import com.example.common_base.http.RetrofitClient
import com.example.module_home.data.Api
import com.example.module_home.firstpage.bean.Article
import com.example.module_home.firstpage.bean.ArticleResponse
import com.example.module_home.firstpage.bean.BannerBean
import com.example.module_home.search.bean.HotKeyBean
import com.example.module_home.search.bean.SearchResultResponse

/**
 * @describe : 网络请求相关操作
 *
 * @author zwq 2020/11/23
 */
class RemoteDataSource : BaseDataOperate() {

    private val api by lazy { RetrofitClient.getInstance().retrofit.create(Api::class.java) }

    suspend fun getArticles(page: Int): BaseResult<ArticleResponse> =
        execute { convert(api.getArticles(page)) }

    suspend fun getTopArticles(): BaseResult<MutableList<Article>>? =
        execute { convert(api.getTopArticles()) }

    suspend fun getBanners(): BaseResult<MutableList<BannerBean>> =
        execute { convert(api.getBanners()) }

    suspend fun getHotKey(): BaseResult<MutableList<HotKeyBean>> =
        execute { convert(api.getHotKey()) }

    suspend fun searchByKey(page: Int, key: String): BaseResult<SearchResultResponse>? =
        execute { convert(api.searchByKey(page, key)) }
}