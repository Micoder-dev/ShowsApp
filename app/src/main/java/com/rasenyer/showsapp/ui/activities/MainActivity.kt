package com.rasenyer.showsapp.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.rasenyer.showsapp.data.models.Show
import com.rasenyer.showsapp.databinding.ActivityMainBinding
import com.rasenyer.showsapp.ui.detail.DetailActivity
import com.rasenyer.showsapp.util.Constants.Companion.SHOW_ID
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val showsViewModel: ShowsViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var showAdapter: ShowAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showAdapter = ShowAdapter()

        setUpRecyclerView()
        getShows()
        observeShows()
        setOnCardViewClick()
        disableViews()

    }

    private fun setUpRecyclerView() {

        binding.mRecyclerView.apply {
            adapter = showAdapter
        }

    }

    private fun getShows() {

        showsViewModel.getShows()

    }

    private fun observeShows() {

        showsViewModel.observeShows().observe(this) {
            showAdapter.setShows(it)
            enableViews()
        }

    }

    private fun setOnCardViewClick() {

        showAdapter.setOnCardViewClick(object : ShowAdapter.OnCardViewClick {

            override fun onClick(show: Show) {

                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra(SHOW_ID, show.id)
                startActivity(intent)

            }

        })

    }

    private fun enableViews() {

        binding.mRecyclerView.visibility = View.VISIBLE
        binding.mProgressBar.visibility = View.GONE

    }

    private fun disableViews() {

        binding.mRecyclerView.visibility = View.GONE
        binding.mProgressBar.visibility = View.VISIBLE

    }

}