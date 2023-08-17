package com.example.mypasswordmanagerment.adapter.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.example.mypasswordmanagerment.R
import com.example.mypasswordmanagerment.data.WelcomeItem

class ViewPageAdapter: PagerAdapter() {
    lateinit var lsItem: List<WelcomeItem>
    lateinit var mContext: Context
    override fun getCount(): Int {
        return lsItem.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun instantiateItem(container: View, position: Int): Any {
        val inflater: LayoutInflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val layoutScreen = inflater.inflate(R.layout.item_welcome,null)

        val imageWelcome: ImageView= layoutScreen.findViewById(R.id.imageWelcome)
        val tv1: TextView = layoutScreen.findViewById(R.id.tv1)
        val tv2: TextView = layoutScreen.findViewById(R.id.tv2)

        tv1.text = lsItem.get(position).title
        tv2.text = lsItem.get(position).title2
        imageWelcome.setImageResource(lsItem.get(position).imageWelcome)

        return layoutScreen
    }
}