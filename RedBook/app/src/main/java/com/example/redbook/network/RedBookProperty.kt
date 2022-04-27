package com.example.redbook

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * 用于展示的数据
 */
class DisplayData(
    var imageSrc:String="", //图片URL
    var title:String="", //标题
    var userImageSrc:String="",//用户头像
    var userName:String="" //用户名
)

/**
 * 用来解析JSON的一堆对象
 */
class ImageInfo(
    val url:String //图片URL
)
class User(
    val images:String, //用户头像URL
    val nickname:String //用户昵称
)
class Note(
    val liked:Boolean,//是否点亮小红星
    val title:String,//标题
    val user:User,//用户信息
    @Json(name = "image_info") val imageInfo:ImageInfo//首页图片信息
)
class Data(val notes:List<Note>){
    fun getDisplayData():MutableList<DisplayData>{

        var res = mutableListOf<DisplayData>()
        for(i in 0 until notes.size ){
            var tmp = DisplayData().apply {
                imageSrc = notes[i].imageInfo.url
                title = notes[i].title
                userImageSrc = notes[i].user.images
                userName = notes[i].user.nickname
            }
            res.add(tmp)
        }
        return res
    }
}

@JsonClass(generateAdapter = true) //moshi自动生成适配器将JSON转换为data class
data class RedBookProperty(
    val data: Data
)