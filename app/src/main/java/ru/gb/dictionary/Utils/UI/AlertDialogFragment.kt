package ru.gb.dictionary.Utils.UI

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.DialogFragment
import ru.gb.dictionary.R

class AlertDialogFragment: AppCompatDialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val context = requireActivity()
        val args = arguments
            val title = args?.getString(TITLE_EXTRA)
            val message = args?.getString(MESSAGE_EXTRA)
        val alertDialog = AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(
                R.string.dialog_button_cancel) { dialog, which ->
                dialog.dismiss()
            }
            .create()


        return alertDialog
    }

    companion object{
        private const val TITLE_EXTRA = "Tittle Message"
        private const val MESSAGE_EXTRA = "Message Extra"

        fun newInstance(title: String?, message: String?) : AlertDialogFragment{
            val dialogFragment = AlertDialogFragment()
            val args = Bundle()
            args.putString(TITLE_EXTRA, title)
            args.putString(MESSAGE_EXTRA, message)
            dialogFragment.arguments = args
            return dialogFragment
        }
    }
}