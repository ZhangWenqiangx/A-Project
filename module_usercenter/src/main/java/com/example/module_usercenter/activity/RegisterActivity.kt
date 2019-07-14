package com.example.module_usercenter.activity

import com.alibaba.android.arouter.facade.annotation.Route
import com.example.common_base.constants.AConstance
import com.example.common_base.base.BaseMVPActivity
import com.example.module_usercenter.R
import com.example.module_usercenter.contract.RegisterContract
import com.example.module_usercenter.presenter.RegisterPresenter
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast

@Route(path = AConstance.ACTIVITY_URL_REGISTER)
class RegisterActivity : BaseMVPActivity<RegisterPresenter>(),RegisterContract.View {
    override fun showLoading() {}

    override fun hideLoading() {}

    override fun getLayoutResId(): Int = R.layout.activity_register

    override fun initView() {
        btn_register.setOnClickListener {
            val username = et_register_username.text.toString().trim { it <= ' ' }
            val password = et_register_password.text.toString().trim { it <= ' ' }
            val repassword = et_register_repassword.text.toString().trim { it <= ' ' }
            presenter.register(username,password,repassword)
        }
    }

    override fun createPresenter(): RegisterPresenter = RegisterPresenter()

    override fun registSuccess() {
        toast("register success")
        finish()
    }
}