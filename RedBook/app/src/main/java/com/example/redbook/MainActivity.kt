package com.example.redbook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.redbook.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel.displayData.observe(this, Observer {newDataList->
            binding.textView.text = newDataList.size.toString()

            for(i in 0 until newDataList.size){
                Log.d("displayData", "${i.toString()}")
                Log.d("displayData", "${newDataList[i].imageSrc}")//图片URL
                Log.d("displayData", "${newDataList[i].title}") //标题
                Log.d("displayData", "${newDataList[i].userImageSrc}")//用户头像
                Log.d("displayData", "${newDataList[i].userName}") //用户名
            }
        })
    }
}