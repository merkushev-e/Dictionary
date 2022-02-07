package ru.gb.dictionary.view.history

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.gb.dictionary.AppState
import ru.gb.dictionary.R
import ru.gb.dictionary.Utils.UI.AlertDialogFragment
import ru.gb.dictionary.databinding.ActivityHistoryBinding
import ru.gb.dictionary.model.data.DataModel
import ru.gb.dictionary.view.main.DescriptionActivity
import ru.gb.dictionary.viewmodel.HistoryViewModel



class HistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistoryBinding
    private val viewModel: HistoryViewModel by viewModel()
    private val adapter: HistoryAdapter by lazy { HistoryAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.liveData.observe(this@HistoryActivity) { renderData(it) }
        initViews()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getData("", false)
    }

     fun setDataToAdapter(data: List<DataModel>) {
        adapter.setData(data)
    }



    private fun initViews() {
        binding.historyActivityRecyclerview.adapter = adapter
    }

    private fun showViewWorking() {
        binding.loadingFrameLayout.visibility = View.GONE
    }

    private fun showViewLoading() {
        binding.loadingFrameLayout.visibility = View.VISIBLE
    }

    protected fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                showViewWorking()
                appState.data?.let {
                    if (it.isEmpty()) {
                        showAlertDialog(
                            getString(R.string.dialog_tittle_sorry),
                            getString(R.string.empty_server_response_on_success)
                        )
                    } else {
                        setDataToAdapter(it)
                    }
                }
            }
            is AppState.Loading -> {
                showViewLoading()
                if (appState.progress != null) {
                    binding.progressBarHorizontal.visibility = View.VISIBLE
                    binding.progressBarRound.visibility = View.GONE
                    binding.progressBarHorizontal.progress = appState.progress
                } else {
                    binding.progressBarHorizontal.visibility = View.GONE
                    binding.progressBarRound.visibility = View.VISIBLE
                }
            }
            is AppState.Error -> {
                showViewWorking()
                appState.error.message?.let { showAlertDialog(getString(R.string.error_stub), it) }
            }
        }
    }

    private fun showAlertDialog(string: String, string1: String) {
        AlertDialogFragment.newInstance(
            getString(R.string.dialog_title_device_is_offline),
            getString(R.string.dialog_message_device_is_offline)
        ).show(
            supportFragmentManager,
            DIALOG_FRAGMENT_TAG)

    }

    companion object{
        private const val DIALOG_FRAGMENT_TAG = "Dialog_Fragment"
    }
}
