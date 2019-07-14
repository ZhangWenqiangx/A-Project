package com.example.common_base.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager

/**
 * Description:所有activity得父类抽取了一些公用方法
 */
abstract class BaseActivity : AppCompatActivity() {

    abstract fun getLayoutResId():Int

    protected abstract fun initView()
    protected abstract fun initData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())

        setStatusBarColor()

        initView()
        initData()
    }

    /**
     * 初始化statusBar颜色
     */
    private fun setStatusBarColor(){

    }
}