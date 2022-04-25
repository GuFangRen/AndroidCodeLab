package com.example.android.trackmysleepquality.sleeptracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.convertDurationToFormatted
import com.example.android.trackmysleepquality.convertNumericQualityToString
import com.example.android.trackmysleepquality.database.SleepNight

class SleepNightAdapter :RecyclerView.Adapter<SleepNightAdapter.ViewHolder>(){
    //为了告知 RecyclerView 其显示的数据已发生更改，请将自定义 setter 添加到位于 SleepNightAdapter 类顶部的 data 变量中。
    // 在 setter 中，为 data 提供一个新值，然后调用 notifyDataSetChanged() 以触发使用新数据重新绘制列表的操作。
    var data = listOf<SleepNight>()
        set(value) {
            field = value
            notifyDataSetChanged()//调用这个以后会重新绘制所有列表
        }

    override  fun getItemCount() = data.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {//给创建的视图填数据
        val item = data[position]
        holder.bind(item)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {//创建视图
        return ViewHolder.from(parent) //静态方法调用
    }

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val sleepLength: TextView = itemView.findViewById(R.id.sleep_length)
        val quality: TextView = itemView.findViewById(R.id.quality_string)
        val qualityImage: ImageView = itemView.findViewById(R.id.quality_image)

        fun bind(item: SleepNight) {
            val res = itemView.context.resources
            sleepLength.text = convertDurationToFormatted(
                    item.startTimeMilli, item.endTimeMilli, res)
            quality.text = convertNumericQualityToString(
                    item.sleepQuality, res)
            qualityImage.setImageResource(when (item.sleepQuality) {
                0 -> R.drawable.ic_sleep_0
                1 -> R.drawable.ic_sleep_1
                2 -> R.drawable.ic_sleep_2
                3 -> R.drawable.ic_sleep_3
                4 -> R.drawable.ic_sleep_4
                5 -> R.drawable.ic_sleep_5
                else -> R.drawable.ic_sleep_active
            })
        }

        companion object {
             fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                //第三个参数填false，因为会在合适的时候自动添加到视图的层次结构中
                val view = layoutInflater.inflate(R.layout.list_item_sleep_night, parent, false)
                return ViewHolder(view)
            }
        }

    }


}