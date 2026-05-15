package com.example.umcpractice

import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    var state1 = false
    var state2 = false
    var state3 = false
    var state4 = false
    var state5 = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val img1 = findViewById<ImageView>(R.id.image1)
        val img2 = findViewById<ImageView>(R.id.image2)
        val img3 = findViewById<ImageView>(R.id.image3)
        val img4 = findViewById<ImageView>(R.id.image4)
        val img5 = findViewById<ImageView>(R.id.image5)

        val text1 = findViewById<TextView>(R.id.text1)
        val text2 = findViewById<TextView>(R.id.text2)
        val text3 = findViewById<TextView>(R.id.text3)
        val text4 = findViewById<TextView>(R.id.text4)
        val text5 = findViewById<TextView>(R.id.text5)



        img1.setOnClickListener {
            state1 = !state1
            if(state1) text1.setTextColor(Color.YELLOW)
            else text1.setTextColor(Color.BLACK)
        }

        img2.setOnClickListener {
            state2 = !state2
            if(state2) text2.setTextColor(Color.BLUE)
            else text2.setTextColor(Color.BLACK)
        }

        img3.setOnClickListener {
            state3 = !state3
            if(state3) text3.setTextColor(Color.GRAY)
            else text3.setTextColor(Color.BLACK)
        }

        img4.setOnClickListener {
            state4 = !state4
            if(state4) text4.setTextColor(Color.GREEN)
            else text4.setTextColor(Color.BLACK)
        }

        img5.setOnClickListener {
            state5 = !state5
            if(state5) text5.setTextColor(Color.RED)
            else text5.setTextColor(Color.BLACK)
        }


    }
    }
