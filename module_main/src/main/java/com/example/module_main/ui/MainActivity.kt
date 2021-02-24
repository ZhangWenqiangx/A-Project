package com.example.module_main.ui

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.example.common_base.base.BaseActivity
import com.example.common_base.constants.AConstance
import com.example.module_main.R
import kotlinx.android.synthetic.main.activity_main.*

@Route(path = AConstance.ACTIVITY_URL_MAIN)
class
MainActivity : BaseActivity(), View.OnClickListener {

    private var currentSelectionId = R.id.rb_home
    private lateinit var fm: FragmentManager
    private var fragments = mutableListOf<Fragment>()

    override fun onClick(v: View?) {

    }

    override fun getLayoutResId(): Int = R.layout.activity_main

    override fun initView() {
        fm = supportFragmentManager
        createFragment()
        selectFragment(0)
        rb_home.isChecked = true

        rg_radio_group.setOnCheckedChangeListener { _, checkedId ->
            if (currentSelectionId == checkedId) {
                return@setOnCheckedChangeListener
            }
            currentSelectionId = checkedId
            when (checkedId) {
                R.id.rb_home -> selectFragment(0)
                R.id.rb_project -> selectFragment(1)
                R.id.rb_mine -> selectFragment(2)
            }

        }
    }

    override fun initData() {

    }

    private fun selectFragment(index: Int) {
        val ft = fm.beginTransaction()
        for (i in fragments.indices) {
            if (i == index) {
                ft.show(fragments[i])
            } else {
                ft.hide(fragments[i])
            }
        }
        ft.commit()
    }

    private fun createFragment() {
        val transaction = fm.beginTransaction()

        val homeFragment = ARouter.getInstance().build(AConstance.FRAGMENT_URL_HOME).navigation() as Fragment
        val videoFragment = ARouter.getInstance().build(AConstance.FRAGMENT_URL_VIDEO).navigation() as Fragment
        val mineFragment = MineFragment()

        transaction.apply {
            add(R.id.fl_main_container, homeFragment)
            add(R.id.fl_main_container, videoFragment)
            add(R.id.fl_main_container, mineFragment)
        }

        fragments.apply {
            add(homeFragment)
            add(videoFragment)
            add(mineFragment)
        }

        transaction.commit()
    }


}
