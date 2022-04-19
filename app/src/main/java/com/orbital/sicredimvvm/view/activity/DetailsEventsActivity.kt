package com.orbital.sicredimvvm.view.activity

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.orbital.core.enums.ExtrasEnum
import com.orbital.sicredimvvm.databinding.ActivityDetailsBinding
import com.orbital.sicredimvvm.db.model.EventsData
import com.orbital.sicredimvvm.di.modules.checkInRepositoryModule
import com.orbital.sicredimvvm.di.modules.checkInServiceModule
import com.orbital.sicredimvvm.di.modules.checkInViewModelModule
import com.orbital.sicredimvvm.view.fragment.DetailsEventsFragment
import org.koin.core.context.loadKoinModules

class DetailsEventsActivity:AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    private val koinModules = listOf(
        checkInRepositoryModule,
        checkInServiceModule,
        checkInViewModelModule
    )
    private val loadModules by lazy {
        loadKoinModules(koinModules)
    }
    private fun inject() = loadModules
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding()
        changeStatusBar()
        replaceFragment(savedInstanceState)
        inject()
    }

    private fun setBinding(){
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    private fun replaceFragment(savedInstanceState: Bundle?){
        if(savedInstanceState == null){
            intent.getParcelableExtra<EventsData>(ExtrasEnum.EVENT.value)
                ?.let { DetailsEventsFragment.newInstance(it) }?.let {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(binding.container.id,
                            it
                        )
                        .commitNow()
                }
        }
    }
    private fun changeStatusBar(){

        if (Build.VERSION.SDK_INT in 19..20) {
            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true)
        }
        window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
        window?.statusBarColor = Color.TRANSPARENT
    }
    private fun setWindowFlag(bits: Int, on: Boolean) {
        val win = window
        val winParams = win?.attributes
        if (on) {
            winParams?.flags = winParams?.flags?.or(bits)
        } else {
            winParams?.flags = winParams?.flags?.and(bits.inv())
        }
        win?.attributes = winParams
    }
}