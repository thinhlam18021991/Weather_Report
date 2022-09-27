package com.assignment.base.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.Window
import com.assignment.base.databinding.DialogSingleButtonBinding

fun Context.createAlertDialog(
    mes: String,
    cancelable: Boolean = false,
    onPositiveBtnClick: (() -> Unit)? = null
): Dialog {
    val inflater = LayoutInflater.from(this)
    val binding = DialogSingleButtonBinding.inflate(inflater)
    val dialogView = binding.root

    val dialog = AlertDialog.Builder(this)
        .apply {
            setView(dialogView)
            setCancelable(cancelable)
        }
        .create()
        .apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
        }
    dialog.setCancelable(false)
    dialogView.run {
        binding.run {
            tvMessage.text = mes
            btOk.setOnClickListener {
                onPositiveBtnClick?.invoke()
                dialog.dismiss()
            }
        }
    }

    dialog.show()
    return dialog
}