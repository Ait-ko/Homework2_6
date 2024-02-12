package com.example.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework2_6.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    private val characterAdapter by lazy {
        CharacterAdapter(this::onClick)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupRecycler()
        //viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.getAllData().observe(this) { charachers ->
            //val adapter = CharacterAdapter(this::onClick, it)
            binding.progressIndicator.isVisible = state is Resource.Loading
            when (state) {
                is Resource.Error -> {
                    Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
                }
                is Resource.Loading -> {}
                is Resource.Success -> {
                    if (state.data != null)
                        characterAdapter.setCharacter(state.data)
                }
            }
        }
        //binding.recyclerView.adapter = adapter
    }

    private fun setupRecycler() = with(binding.recyclerView) {
        layoutManager = LinearLayoutManager(
            this@MainActivity,
            LinearLayoutManager.VERTICAL,
            false
        )
        adapter = characterAdapter
    }

    private fun onClick(character: Character) {
        /*val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra(CHARACTER_ID_ARG, character.id)
        startActivity(intent)*/

        startActivity(
            Intent(
                this, SecondActivity::class.java
            ).apply {
                putExtra(
                    CHARACTER_ID_ARG,
                    character.id
                )
            }
        )
    }

    companion object {
        const val CHARACTER_ID_ARG = "key"
    }
}