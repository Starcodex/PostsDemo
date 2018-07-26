package com.julianotalora.posts.util

import android.content.Context
import android.support.design.widget.TabLayout
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.LinearLayout

/**
 * Created by Bonestack on 24/07/2018.
 */
class CustomTabLayout : TabLayout {

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}


    override fun onTouchEvent(ev: MotionEvent): Boolean {

        return super.onTouchEvent(ev)
    }


    fun setPagingEnabled(b: Boolean) {
        val tabStrip = getChildAt(0) as LinearLayout
        for (i in 0 until tabStrip.childCount) {
            tabStrip.getChildAt(i).isClickable = b
        }
    }
}