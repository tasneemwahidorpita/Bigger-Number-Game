package com.example.biggernumbergame

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var leftButton: Button
    private lateinit var rightButton: Button
    private lateinit var resultText: TextView
    private lateinit var scoreText: TextView

    private var leftValue = 0
    private var rightValue = 0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        leftButton = findViewById(R.id.leftButton)
        rightButton = findViewById(R.id.rightButton)
        resultText = findViewById(R.id.resultText)
        scoreText = findViewById(R.id.scoreText)

        leftButton.setOnClickListener { handleGuess(true) }
        rightButton.setOnClickListener { handleGuess(false) }

        generateNewRound()
        updateScoreDisplay()
    }

    private fun handleGuess(choseLeft: Boolean) {
        val selectedValue = if (choseLeft) leftValue else rightValue
        val largerValue = maxOf(leftValue, rightValue)

        if (selectedValue == largerValue) {
            score += 1
            resultText.text = "Correct!"
        } else {
            score -= 1
            resultText.text = "Wrong!"
        }

        updateScoreDisplay()
        generateNewRound()
    }

    private fun generateNewRound() {
        leftValue = (0..9).random()
        rightValue = (0..9).random()

        while (leftValue == rightValue) {
            rightValue = (0..9).random()
        }

        leftButton.text = leftValue.toString()
        rightButton.text = rightValue.toString()
    }

    private fun updateScoreDisplay() {
        scoreText.text = "Points: $score"
    }
}
