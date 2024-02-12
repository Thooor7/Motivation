package com.example.motivation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.example.motivation.insfra.MotivationConstants.KEY.USER_NAME
import com.example.motivation.R
import com.example.motivation.data.Mock
import com.example.motivation.insfra.SecurityPreference
import com.example.motivation.databinding.ActivityMainBinding
import com.example.motivation.insfra.MotivationConstants.FILTE.HAPPY
import com.example.motivation.insfra.MotivationConstants.FILTE.ALL
import com.example.motivation.insfra.MotivationConstants.FILTE.SUNNY

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private var categoryId = ALL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btNewPhrase.setOnClickListener(this)

        binding.ivAll.setOnClickListener(this)
        binding.ivHappy.setOnClickListener(this)
        binding.ivSunny.setOnClickListener(this)
        binding.tvOla.setOnClickListener(this)

        handleFilter(R.id.iv_all)
        handleNextPhrase()
    }

    override fun onResume() {
        super.onResume()
        showName()
    }

    override fun onClick(view: View) {
        if (view.id == R.id.bt_new_phrase) {
            handleNextPhrase()
        } else if (view.id in listOf(R.id.iv_all, R.id.iv_happy, R.id.iv_sunny)) {
            handleFilter(view.id)
            handleNextPhrase()
        } else if (view.id == R.id.tv_ola) {
            startActivity(Intent(this, UsersActivity::class.java))
        }
    }

    private fun handleNextPhrase() {
        binding.tvTextPhrase.text = Mock().getPhrase(categoryId)
    }

    private fun handleFilter(id: Int) {
        binding.apply {
            ivAll.setColorFilter(ContextCompat.getColor(applicationContext, R.color.dark_purple))
            ivHappy.setColorFilter(ContextCompat.getColor(applicationContext, R.color.dark_purple))
            ivSunny.setColorFilter(ContextCompat.getColor(applicationContext, R.color.dark_purple))
        }

        when (id) {
            R.id.iv_all -> {
                binding.ivAll.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = ALL
            }

            R.id.iv_happy -> {
                binding.ivHappy.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = HAPPY
            }

            else -> {
                binding.ivSunny.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = SUNNY
            }
        }

    }

    private fun showName() {
        val name = SecurityPreference(this).getString(USER_NAME)
        binding.tvOla.text = "Olá, ${name}"
    }

}