package com.bignerdranch.android.jeremybui_sharepreferences

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var imageView: ImageView
    private lateinit var editText: EditText
    private var lastSelectedImageId: Int = -1
    private val prefsName = "AppPrefs"
    private val keyImageId = "image_id"
    private val keyText = "text"

    private val imageIds = arrayOf(
        R.drawable.apple,
        R.drawable.orange,
        R.drawable.banana,
        R.drawable.kiwi,
        R.drawable.watermelon
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView = findViewById(R.id.imageView)
        editText = findViewById(R.id.editText)
        val button: Button = findViewById(R.id.button)

        button.setOnClickListener {
            val randomIndex = Random.nextInt(imageIds.size)
            lastSelectedImageId = imageIds[randomIndex]
            imageView.setImageResource(lastSelectedImageId)
        }

        val prefs: SharedPreferences = getSharedPreferences(prefsName, MODE_PRIVATE)
        editText.setText(prefs.getString(keyText, ""))
        val savedImageId = prefs.getInt(keyImageId, -1)
        if (savedImageId != -1) {
            imageView.setImageResource(savedImageId)
            lastSelectedImageId = savedImageId
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        getSharedPreferences(prefsName, MODE_PRIVATE).edit().apply {
            putString(keyText, editText.text.toString())
            putInt(keyImageId, lastSelectedImageId)
            apply()
        }
    }
}
