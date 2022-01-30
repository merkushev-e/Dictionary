package ru.gb.dictionary.view.main

import android.os.Bundle
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ru.gb.dictionary.AppState
import ru.gb.dictionary.view.searchdialog.SearchDialogFragment
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.gb.dictionary.R
import ru.gb.dictionary.databinding.ActivityMainBinding
import ru.gb.dictionary.viewmodel.MainViewModel



class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel


    companion object {
        private const val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG =
            "BOTTOM_SHEET_FRAGMENT"
    }



    private lateinit var binding: ActivityMainBinding
    private var adapter: MainAdapter? = null


    private val onListItemClickListener =
        MainAdapter.OnListItemClickListener { data ->
            Toast.makeText(this@MainActivity, data.text, Toast.LENGTH_SHORT).show()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        initDrawer(initToolbar())
        val model: MainViewModel by viewModel()
        viewModel = model

        binding.appBarMain.mainContent.searchFab.setOnClickListener {
            val searchDialogFragment = SearchDialogFragment.newInstance()
            searchDialogFragment.setOnSearchClickListener { searchWord ->
                viewModel.getData(searchWord, true)
                viewModel.liveData.observe(this, { appState ->
                    renderData(appState)
                })

            }
            searchDialogFragment.show(
                supportFragmentManager,
                BOTTOM_SHEET_FRAGMENT_DIALOG_TAG)
        }
    }


     private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                val dataModel = appState.data
                if (dataModel == null || dataModel.isEmpty()) {
                    showErrorScreen(getString(R.string.empty_server_response_on_success))
                } else {
                    showViewSuccess()
                    if (adapter == null) {
                        binding.appBarMain.mainContent.mainActivityRecyclerview.layoutManager =
                            LinearLayoutManager(applicationContext)
                        binding.appBarMain.mainContent.mainActivityRecyclerview.adapter =
                            MainAdapter(onListItemClickListener, dataModel)
                    } else {
                        adapter!!.setData(dataModel)
                    }
                }
            }
            is AppState.Loading -> {
                showViewLoading()
                if (appState.progress != null) {
                    binding.appBarMain.mainContent.progressBarHorizontal.visibility = VISIBLE
                    binding.appBarMain.mainContent.progressBarRound.visibility = GONE
                    binding.appBarMain.mainContent.progressBarHorizontal.progress =
                        appState.progress
                } else {
                    binding.appBarMain.mainContent.progressBarHorizontal.visibility = GONE
                    binding.appBarMain.mainContent.progressBarRound.visibility = VISIBLE
                }
            }
            is AppState.Error -> {
                showErrorScreen(appState.error.message)
            }
        }


    }


    private fun showErrorScreen(error: String?) {
        showViewError()
        binding.appBarMain.mainContent.errorTextview.text =
            error ?: getString(R.string.undefined_error)
        binding.appBarMain.mainContent.reloadButton.setOnClickListener {
            viewModel.getData("hi",true)
            viewModel.liveData.observe(this) { appState ->
                renderData(appState)
            }
        }
    }

    private fun showViewSuccess() {
        binding.appBarMain.mainContent.successLinearLayout.visibility = VISIBLE
        binding.appBarMain.mainContent.loadingFrameLayout.visibility = GONE
        binding.appBarMain.mainContent.errorLinearLayout.visibility = GONE
    }

    private fun showViewLoading() {
        binding.appBarMain.mainContent.successLinearLayout.visibility = GONE
        binding.appBarMain.mainContent.loadingFrameLayout.visibility = VISIBLE
        binding.appBarMain.mainContent.errorLinearLayout.visibility = GONE
    }

    private fun showViewError() {
        binding.appBarMain.mainContent.successLinearLayout.visibility = GONE
        binding.appBarMain.mainContent.loadingFrameLayout.visibility = GONE
        binding.appBarMain.mainContent.errorLinearLayout.visibility = VISIBLE
    }


    private fun initToolbar(): Toolbar {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        return toolbar
    }

    private fun initDrawer(toolbar: Toolbar) {
        val drawer: DrawerLayout = findViewById(R.id.drawer_layout)
        val toggle: ActionBarDrawerToggle = ActionBarDrawerToggle(
            this, drawer, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )

        drawer.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(object :
            NavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when (item.itemId) {
                    R.id.action_drawer_settings -> {
                        Toast.makeText(
                            applicationContext, "Settings has opened",
                            Toast.LENGTH_SHORT
                        ).show()
                        return true
                    }
                    R.id.action_drawer_history -> {
                        Toast.makeText(
                            applicationContext, "Settings has opened",
                            Toast.LENGTH_SHORT
                        ).show()
                        return true
                    }
                }
                return false
            }
        })

    }


}


