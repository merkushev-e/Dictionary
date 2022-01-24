package ru.gb.dictionary.view.main

import android.os.Bundle
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.recyclerview.widget.LinearLayoutManager
import ru.gb.dictionary.AppState



import ru.gb.dictionary.model.data.DataModel
import ru.gb.dictionary.presenter.MainPresenterImpl
import ru.gb.dictionary.presenter.Presenter
import ru.gb.dictionary.view.BaseActivity
import ru.gb.dictionary.view.searchdialog.SearchDialogFragment
import ru.gb.dictionary.view.View


import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView


import ru.gb.dictionary.R
import ru.gb.dictionary.databinding.ActivityMainBinding


class MainActivity : BaseActivity<AppState>() {

    companion object {
        private const val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG =
            "BOTTOM_SHEET_FRAGMENT"
    }

    private lateinit var binding: ActivityMainBinding
    private var adapter: MainAdapter? = null


    private val onListItemClickListener: MainAdapter.OnListItemClickListener =
        object : MainAdapter.OnListItemClickListener {
            override fun onItemClick(data: DataModel) {
                Toast.makeText(this@MainActivity, data.text, Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initDrawer(initToolbar())

        binding.appBarMain.mainContent.searchFab.setOnClickListener {
            val searchDialogFragment = SearchDialogFragment.newInstance()
            searchDialogFragment.setOnSearchClickListener(object :
                SearchDialogFragment.OnSearchClickListener {
                override fun onClick(searchWord: String) {
                    presenter.getData(searchWord, true)
                }
            })

            searchDialogFragment.show(supportFragmentManager, BOTTOM_SHEET_FRAGMENT_DIALOG_TAG)
        }


    }

    override fun createPresenter(): Presenter<AppState, View> {
        return MainPresenterImpl()
    }

    override fun renderData(appState: AppState) {
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
                    binding.appBarMain.mainContent.progressBarHorizontal.progress = appState.progress
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
        binding.appBarMain.mainContent.errorTextview.text = error ?: getString(R.string.undefined_error)
        binding.appBarMain.mainContent.reloadButton.setOnClickListener {
            presenter.getData("hi", true)
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
                        Toast.makeText(applicationContext, "Settings has opened",
                            Toast.LENGTH_SHORT).show()
                        return true
                    }
                    R.id.action_drawer_history -> {
                        Toast.makeText(applicationContext, "Settings has opened",
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

