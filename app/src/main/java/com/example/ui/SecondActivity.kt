package com.example.ui

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.data.BaseActivity
import com.example.homework2_6.R
import com.example.homework2_6.Status
import com.example.homework2_6.databinding.ActivitySecondBinding
import com.example.model.Character
import com.example.utils.Resource
import dagger.hilt.android.AndroidEntryPoint


class SecondActivity : BaseActivity() {

    private val binding: ActivitySecondBinding by lazy {
        ActivitySecondBinding.inflate(layoutInflater)
    }
    private val viewModel: SecondViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val id = intent.getIntExtra(CHARACTER_ID_ARG, 0)

        viewModel.charactersLv.stateObserver(
            success = {
                setCharacterData(it)
            },
            state = { binding.progressIndicator.isVisible = it is Resource.Loading }
        )

        viewModel.getCharacter(id)

        /*viewModel.getData(id).observe(this) { state ->
            when (state) {
                is Resource.Error -> {
                    Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
                }

                is Resource.Loading -> {}
                is Resource.Success -> {
                    if (state.data != null) {
                        setCharacterData(state.data)
                    }
                }
            }
        }*/
    }

    private fun setCharacterData(it: Character) = with(binding) {
        tvName.text = it.name
        tvLocation.text = it.location.toString()
        tvOrigin.text = it.origin.toString()
        tvSpecies.text = it.species
        tvStatus.text = it.status
        tvGender.text = it.gender
        Glide.with(image).load(it.image).into(image)

        val statusImage = Status(imgCircleStatus)
        statusImage.setStatusImage(it.status)

        val episodeData = arrayOf(
            it.name,
            it.species,
            it.gender
        )

        val spinner = binding.spinner

        val adapter = ArrayAdapter(
            this@SecondActivity,
            R.layout.custom_spinner,
            R.id.tv_custom_spinner,
            episodeData
        )
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View?,
                position: Int,
                id: Long
            ) {
                spinner.setSelection(0, true)
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
            }
        }
    }

    companion object {
        const val CHARACTER_ID_ARG = "key"
    }
}