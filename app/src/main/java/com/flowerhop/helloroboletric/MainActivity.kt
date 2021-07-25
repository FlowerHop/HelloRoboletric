package com.flowerhop.helloroboletric

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.flowerhop.helloroboletric.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var stateTrace: LifeStateTrace = LifeStateTrace()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(stateTrace)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val repository = CountRepository.create(this)
        val viewModelFactory = AnyViewModelFactory {
            CountViewModel(repository)
        }

        val countViewModel: CountViewModel = viewModelFactory.create(CountViewModel::class.java)
        countViewModel.count.observe(this,
            { t -> binding.btnHello.text = "Click $t" })

        binding.btnHello.setOnClickListener {
            countViewModel.addCount()
        }
    }
}