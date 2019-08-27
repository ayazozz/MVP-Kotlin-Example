package com.example.recipeapp.ui.firstLaunch

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import com.example.recipeapp.R
import com.example.recipeapp.ui.displayAllRecipes.DisplayRecipesActivity

class FirstLaunchActivity : AppCompatActivity() {

    private lateinit var welcomeText: TextView
    private lateinit var nextBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_launch)

        welcomeText = findViewById(R.id.welcome_textview)
        nextBtn = findViewById(R.id.next_btn)

        nextBtnClickOnListener()

        welcomeText.startAnimation(AnimationUtils.loadAnimation(this@FirstLaunchActivity, android.R.anim.slide_in_left))

        Handler().postDelayed(
            {
                val intent = Intent(applicationContext, DisplayRecipesActivity::class.java)
                startActivity(intent)
            },
            3000 // value in milliseconds
        )

    }


    private fun nextBtnClickOnListener() {

        nextBtn.setOnClickListener {

            val intent = Intent(applicationContext, DisplayRecipesActivity::class.java)
            startActivity(intent)
        }

    }
}
