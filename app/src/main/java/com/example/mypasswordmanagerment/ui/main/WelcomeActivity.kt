package com.example.mypasswordmanagerment.ui.main

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.mypasswordmanagerment.R
import com.example.mypasswordmanagerment.adapter.adapter.ViewPageAdapter
import com.example.mypasswordmanagerment.data.WelcomeItem
import com.google.android.material.tabs.TabLayout


class WelcomeActivity: AppCompatActivity() {
    private lateinit var view_pager2: ViewPager
    private lateinit var viewPagerAdapter: ViewPageAdapter
    private lateinit var tab_indicator: TabLayout
    private lateinit var btnNext: Button
    private lateinit var btnGetStarted: Button
    var position = 0
    private lateinit var btnAnim: Animation


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // make the activity on full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        if (restorePrefData()) {
            val mainActivity = Intent(applicationContext, MainActivity::class.java)
            startActivity(mainActivity)
            finish()
        }
        setContentView(R.layout.welcome_fragment)

        // hide the action bar
        supportActionBar!!.hide()

        // ini views
        tab_indicator = findViewById(R.id.tab_indicator)
        btnNext = findViewById(R.id.btnNext)
        btnGetStarted = findViewById(R.id.btnGetStarted)
        btnAnim = AnimationUtils.loadAnimation(applicationContext,R.anim.button_animation)

        // fill list screen
        val mList: MutableList<WelcomeItem> = ArrayList()
        mList.add(
            WelcomeItem(
                "Get Security & Encrypted Storage",
                "Bring all your passwords under one roof. Your password is highly encrypted and cannot be steal",
                R.drawable.img1
            )
        )
        mList.add(
            WelcomeItem(
                "Notifications",
                "Get notified about important events like not to forget to create a back up",
                R.drawable.img2
            )
        )
        mList.add(
            WelcomeItem(
                "Categorrization & Password History",
                "Categorize and view your password history over time for different websites and always stay secured",
                R.drawable.img3
            )
        )
        mList.add(
            WelcomeItem(
                "Add Custom Fields & Attach Photos",
                "Add new custom fields and attach photos or take photos and store information inside the app",
                R.drawable.img2
            )
        )
        // setup viewpager
        view_pager2 = findViewById(R.id.view_pager2)
        viewPagerAdapter = ViewPageAdapter()
        view_pager2.adapter = viewPagerAdapter
//         setup tablayout with viewpager
        tab_indicator.setupWithViewPager(view_pager2)

        btnGetStarted.setOnClickListener(View.OnClickListener {
            val mainActivity = Intent(applicationContext, MainActivity::class.java)
            startActivity(mainActivity)
            savePrefsData()
            finish()
        })

        btnNext.setOnClickListener(View.OnClickListener {
            position = view_pager2.currentItem
            if (position < mList.size) {
                position++
                view_pager2.setCurrentItem(position)
            }
            if (position == mList.size -1) {
                loadLastScreen()
            }
        })


        tab_indicator.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab!!.position == mList.size -1) {
                    loadLastScreen()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}

        })

    }

    fun restorePrefData(): Boolean{
        val pre = applicationContext.getSharedPreferences("my pref", MODE_PRIVATE)
        val isIntroActivity = pre.getBoolean("IsIntroOpenned",false)
        return isIntroActivity
    }

    fun savePrefsData() {
        val pref = applicationContext.getSharedPreferences("myPref", MODE_PRIVATE)
        val editor: SharedPreferences.Editor = pref.edit()
        editor.putBoolean("isIntroOpenned",true)
        editor.commit()
    }

    fun loadLastScreen() {
        btnNext.visibility = View.INVISIBLE
        tab_indicator.visibility = View.VISIBLE
        btnGetStarted.visibility = View.VISIBLE

        btnGetStarted.animation = btnAnim
    }
}