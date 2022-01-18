package com.cesarvaliente.dialogs_sample

import android.app.AlertDialog
import android.os.Bundle
import android.view.Gravity
import android.view.MenuInflater
import android.view.View
import android.view.WindowManager
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import com.cesarvaliente.dialogs_sample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private enum class Side { LEFT, RIGHT, CENTER }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Dialogs
        binding.showDialogLeft.setOnClickListener {
            showDialog(Side.LEFT)
        }
        binding.showDialogCenter.setOnClickListener {
            showDialog(Side.CENTER)
        }
        binding.showDialogRight.setOnClickListener {
            showDialog(Side.RIGHT)
        }

        //PopUpMenu
        binding.contextMenuLeft.setOnClickListener {
            showPopUpMenu(it, Side.LEFT)
        }
        binding.contextMenuCenter.setOnClickListener {
            showPopUpMenu(it, Side.CENTER)
        }
        binding.contextMenuRight.setOnClickListener {
            showPopUpMenu(it, Side.RIGHT)
        }
    }

    private fun showDialog(side: Side) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Hello world!").setMessage("Side ${side.name} !!")

        val dialog = builder.create()
        val window = dialog.window
        window?.let {
            val wlp: WindowManager.LayoutParams = it.attributes
            wlp.gravity = when (side) {
                Side.LEFT -> Gravity.LEFT
                Side.RIGHT -> Gravity.RIGHT
                else -> Gravity.CENTER
            }
            window.attributes = wlp
        }
        dialog.show()
    }

    private fun showPopUpMenu(view: View, side: Side) {
        val gravity = when (side) {
            Side.LEFT -> Gravity.START
            Side.RIGHT -> Gravity.END
            else -> Gravity.CENTER
        }
        val popup = PopupMenu(this, view, gravity)
        val inflater: MenuInflater = popup.menuInflater
        inflater.inflate(R.menu.menu_layout, popup.menu)

        popup.show()
    }
}