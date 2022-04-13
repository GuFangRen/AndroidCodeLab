/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import com.example.android.navigation.databinding.ActivityMainBinding
import androidx.navigation.ui.NavigationUI

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        @Suppress("UNUSED_VARIABLE")
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        drawerLayout = binding.drawerLayout

        val navController=this.findNavController(R.id.myNavHostFragment)
        //设置返回键UI的，并把返回键关联到navController
        NavigationUI.setupActionBarWithNavController(this,navController,drawerLayout)
        //设置滑动菜单UI，并把滑动菜单关联到nacController
        NavigationUI.setupWithNavController(binding.navView,navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController=this.findNavController(R.id.myNavHostFragment)
        //return navController.navigateUp()
        return NavigationUI.navigateUp(navController,drawerLayout)
    }

    // TODO (01) Create the new TitleFragment
    // Select File->New->Fragment->Fragment (Blank)

    // TODO (02) Clean up the new TitleFragment
    // In our new TitleFragment

    // TODO (03) Use DataBindingUtil.inflate to inflate and return the titleFragment in onCreateView
    // In our new TitleFragment
    // R.layout.fragment_title
}
