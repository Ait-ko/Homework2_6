package com.example.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.homework2_6.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        //viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.getAllData().observe(this) {
            val adapter = CharacterAdapter(this::onClick, it)
            binding.recyclerView.adapter = adapter
        }

    }

    private fun onClick(character: Character) {
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra("key", character.charValue())
        startActivity(intent)
    }
}