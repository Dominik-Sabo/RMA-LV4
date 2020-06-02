package com.sabo.dominik.viewmodeletc

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.sabo.dominik.viewmodeletc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val counterViewModel: CounterViewModel by viewModels()
    lateinit var sharedPreferences: SharedPreferences

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.counterViewModel = counterViewModel

        val countObserver = Observer<Int>{ newCount ->
            binding.tvCounter.text = newCount.toString()
        }

        val colourObserver = Observer<Int>{ newColour ->
            binding.tvCounter.setBackgroundColor(newColour)
        }

        counterViewModel.currentCount.observe(this, countObserver)
        counterViewModel.currentColour.observe(this, colourObserver)


        sharedPreferences = getSharedPreferences("Settings", Context.MODE_PRIVATE)
        setUpClickListeners()
        initiateCounter()
    }

    override fun onPause() {
        saveToSharedPreferences()
        super.onPause()
    }

    override fun onStop() {
        saveToSharedPreferences()
        super.onStop()
    }

    fun initiateCounter(){
        loadFromSharedPreferences()
        if(counterViewModel.currentCount.value == null) {
            counterViewModel.currentCount.value = 0
        }
    }


    fun setUpClickListeners()
    {
        binding.btnWhite.setOnClickListener{
            counterViewModel.currentColour.value = Color.WHITE
            counterViewModel.increment()
        }

        binding.btnBlack.setOnClickListener{
            counterViewModel.currentColour.value = Color.BLACK
            counterViewModel.increment()
        }

        binding.btnBlue.setOnClickListener{
            counterViewModel.currentColour.value = Color.BLUE
            counterViewModel.increment()
        }

        binding.btnYellow.setOnClickListener{
            counterViewModel.currentColour.value = Color.YELLOW
            counterViewModel.increment()
        }

        binding.btnReset.setOnClickListener{
            counterViewModel.reset()
        }
    }

    fun saveToSharedPreferences(){
        with (sharedPreferences.edit()) {
            counterViewModel.currentCount.value?.let { putInt("count", it) }
            counterViewModel.currentColour.value?.let { putInt("colour", it) }
            commit()
        }
    }

    fun loadFromSharedPreferences(){
        counterViewModel.currentCount.value = sharedPreferences.getInt("count", 0)
        counterViewModel.currentColour.value = sharedPreferences.getInt("colour", Color.CYAN)
    }
}
