package com.cesarvaliente.dialogs_sample

import android.app.AlertDialog
import android.os.Bundle
import android.view.Gravity
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.cesarvaliente.dialogs_sample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private enum class Side { LEFT, RIGHT, CENTER }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.showDialogLeft.setOnClickListener {
            showDialog(Side.LEFT)
        }
        binding.showDialogCenter.setOnClickListener {
            showDialog(Side.CENTER)
        }
        binding.showDialogRight.setOnClickListener {
            showDialog(Side.RIGHT)
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
}