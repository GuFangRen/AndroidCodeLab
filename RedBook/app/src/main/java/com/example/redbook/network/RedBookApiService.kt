package com.example.redbook.network

import com.example.redbook.RedBookProperty
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "http://192.168.0.103/" //同个局域网的Apache服务器

/**
 * 应用了自动生成的适配器的Moshi对象
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * 使用Moshi对象创建Retrofit对象，这样Retrofit对象可以直接转换JSON->data class
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

/**
 * 定义接口供Retrofit对象实现 , 这是该文件的主类 。
 */
interface RedBookApiService {
    @GET("ListMockData.json")
    suspend fun getProperty(): RedBookProperty //该接口结合Retrofit可以直接json->data class
}

object RedBookApi{
    //这里把这个变量用单例类包起来是为了调用方便并且可以延迟初始化
    //Retrofit 方法使用接口创建 Retrofit 服务本身。由于此调用的计算成本很高，因此您会懒惰地初始化 Retrofit 服务。
    val retrofitService : RedBookApiService by lazy { retrofit.create(RedBookApiService::class.java) }
}