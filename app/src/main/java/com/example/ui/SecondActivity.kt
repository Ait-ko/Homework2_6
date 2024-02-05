package com.example.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.homework2_6.R
import com.example.homework2_6.databinding.ActivitySecondBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SecondActivity : AppCompatActivity() {

    private val binding: ActivitySecondBinding by lazy {
        ActivitySecondBinding.inflate(layoutInflater)
    }
    private val viewModel: SecondViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val id = intent.getIntExtra("key", 0)

        viewModel.getData(id).observe(this) {
            it?.let {
                setCharacterData(it)
            }
        }

    }

    private fun setCharacterData(it: Character) = with(binding) {
        tvName.text = it.name
        tvLocation.text = it.location.toString()
        tvOrigin.text = it.origin.toString()
        tvSpecies.text = it.species
        tvStatus.text= it.status
        Glide.with(image).load(it.image).into(image)

        when (it.status) {
            "Alive" -> imgCircleStatus.setBackgroundResource(R.drawable.alive)
            "Dead" -> imgCircleStatus.setBackgroundResource(R.drawable.death)
            "Unknown" -> imgCircleStatus.setBackgroundResource(R.drawable.unknown)
        }
    }


}
