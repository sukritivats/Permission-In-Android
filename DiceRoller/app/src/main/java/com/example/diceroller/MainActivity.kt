package com.example.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import java.util.Random

// AppCompatActivity() is the base class and is used so that app is compatible to all older versions
class MainActivity : AppCompatActivity() {

    // we can use null operator but with lateinit i will
    // make sure to initialize it in future
    // now it is not null and it will not be null in future
    lateinit var diceImage : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)  // ctrl b
        // It inflates the layout resource R.layout.activity_main,
        // which defines the UI layout for the MainActivity
        // R represent resource folder
        // R is a resource class which binds layouts, values etc

        val rollButton: Button = findViewById(R.id.roll_button)
        //rollButton.text="Lets Roll"
        rollButton.setOnClickListener {
            // Toast.makeText(this,"Button clicked",Toast.LENGTH_SHORT).show()
            rollDice()

        }
        diceImage=findViewById(R.id.dice_image)

    }

    private fun rollDice() {
       // val resultText : TextView =findViewById(R.id.result_text)
        //resultText.text="Dice Rolled"
       // val randomInt = Random().nextInt(6)+1 // generate 0 to 6
        // resultText.text=randomInt.toString()

        val drawableResource = when(Random().nextInt(6)+1)
        {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }

        // val diceImage : ImageView = findViewById(R.id.dice_image)
        // it is not efficient
        diceImage.setImageResource(drawableResource)

    }
}