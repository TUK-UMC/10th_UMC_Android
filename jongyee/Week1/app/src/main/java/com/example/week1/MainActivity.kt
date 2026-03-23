package com.example.week1
import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var tvHappy: TextView
    private lateinit var tvExcited: TextView
    private lateinit var tvNormal: TextView
    private lateinit var tvAnxious: TextView
    private lateinit var tvAngry: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ivHappy = findViewById<ImageView>(R.id.iv_happy)
        val ivExcited = findViewById<ImageView>(R.id.iv_excited)
        val ivNormal = findViewById<ImageView>(R.id.iv_normal)
        val ivAnxious = findViewById<ImageView>(R.id.iv_anxious)
        val ivAngry = findViewById<ImageView>(R.id.iv_angry)

        tvHappy = findViewById(R.id.tv_happy)
        tvExcited = findViewById(R.id.tv_excited)
        tvNormal = findViewById(R.id.tv_normal)
        tvAnxious = findViewById(R.id.tv_anxious)
        tvAngry = findViewById(R.id.tv_angry)


        ivHappy.setOnClickListener {
            resetTextColors()
            tvHappy.setTextColor(Color.YELLOW)
        }

        ivExcited.setOnClickListener {
            resetTextColors()
            tvExcited.setTextColor(Color.BLUE)
        }

        ivNormal.setOnClickListener {
            resetTextColors()
            tvNormal.setTextColor(Color.MAGENTA)
        }

        ivAnxious.setOnClickListener {
            resetTextColors()
            tvAnxious.setTextColor(Color.GREEN)
        }

        ivAngry.setOnClickListener {
            resetTextColors()
            tvAngry.setTextColor(Color.RED)
        }
    }

    private fun resetTextColors() {
        tvHappy.setTextColor(Color.BLACK)
        tvExcited.setTextColor(Color.BLACK)
        tvNormal.setTextColor(Color.BLACK)
        tvAnxious.setTextColor(Color.BLACK)
        tvAngry.setTextColor(Color.BLACK)
    }
}