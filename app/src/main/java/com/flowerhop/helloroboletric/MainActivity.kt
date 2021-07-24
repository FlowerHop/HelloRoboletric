package com.flowerhop.helloroboletric

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import com.flowerhop.helloroboletric.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var count = 0
    var stateTrace: LifeStateTrace = LifeStateTrace()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(stateTrace)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnHello.setOnClickListener {
            (it as AppCompatButton).text = "Click ${++count}"
        }
    }
}