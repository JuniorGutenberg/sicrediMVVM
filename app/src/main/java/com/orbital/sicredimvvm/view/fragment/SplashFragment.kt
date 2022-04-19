package com.orbital.sicredimvvm.view.fragment

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.orbital.sicredimvvm.view.activity.MainActivity
import com.orbital.sicredimvvm.R
import com.orbital.sicredimvvm.databinding.FragmentSplashBinding
import com.orbital.sicredimvvm.viewmodel.SplashViewModel

class SplashFragment: Fragment() {
    companion object{
        fun newInstance() = SplashFragment()
    }

    private lateinit var viewModel: SplashViewModel
    private lateinit var binding: FragmentSplashBinding


    private lateinit var animationTop: Animation
    private lateinit var animationBottom: Animation
    private val SEGUNDOS:Long = 5000
    private val INTERVALO:Long = 1000

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(inflater)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setAnimation()
        countDown()
        viewModel = ViewModelProvider(this)[SplashViewModel::class.java]
    }

    private fun setAnimation(){
        animationTop = AnimationUtils.loadAnimation(context, R.anim.top_to_bottom_splash)
        animationBottom = AnimationUtils.loadAnimation(context, R.anim.bottom_to_top_splash)

        binding.apply {
            iv.animation = animationTop
            tvDesenvolvido.animation = animationBottom
            tvProcesso.animation = animationBottom
        }
    }
    private fun countDown(){
        val timer = object: CountDownTimer(SEGUNDOS, INTERVALO) {
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
                finalizar()
            }
        }
        timer.start()
    }
    private fun finalizar(){
        val intent = Intent(context, MainActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }
}