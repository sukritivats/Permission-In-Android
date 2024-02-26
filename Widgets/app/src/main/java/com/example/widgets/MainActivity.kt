package com.example.widgets

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.widgets.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    var binding: ActivityMainBinding? = null

    var numberOne: String = ""
    var numberTwo: String = ""
    var isOperandClicked: Boolean = false
    var operandType: OperandsType = OperandsType.DEFAULT

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        // clickHandler()
        calculation()
        setSPinner()
    }

    private fun setSPinner() {

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            arrayOf(
                "Manish", "Apoorva", "Sidrajit"
            )
        )

        binding?.spinner?.setOnItemClickListener { parent, view, position, id ->

        }

        binding?.spinner?.adapter = adapter
    }

    private fun clear() {
        numberOne = ""
        numberTwo = ""
        binding?.input?.text = ""
        binding?.output?.text = ""
        isOperandClicked = false
        operandType = OperandsType.DEFAULT
    }

    private fun clearLastDigit() {
        if (!isOperandClicked) {
            if (numberOne.isNotEmpty()) { // Check if numberOne is not empty before clearing
                numberOne = numberOne.dropLast(1) // Remove the last character from numberOne
                binding?.input?.text = numberOne
            }
        } else {
            if (numberTwo.isNotEmpty()) {
                numberTwo = numberTwo.dropLast(1)
                binding?.input?.text = numberTwo
            }
        }
    }

    private fun calculation() {
        binding?.btnCross?.setOnClickListener {
            clearLastDigit()
        }
        binding?.clear?.setOnClickListener {
            clear()
        }
        binding?.btnOne?.setOnClickListener {
            if (!isOperandClicked) {
                numberOne += binding?.btnOne?.text ?: ""
                binding?.input?.text = numberOne.toString()
            } else {
                numberTwo += binding?.btnOne?.text ?: ""
                binding?.input?.text = numberTwo.toString()
            }
        }
        binding?.btnTwo?.setOnClickListener {
            if (!isOperandClicked) {
                numberOne += binding?.btnTwo?.text ?: ""
                binding?.input?.text = numberOne.toString()
            } else {
                numberTwo += binding?.btnTwo?.text ?: ""
                binding?.input?.text = numberTwo.toString()
            }
        }
        binding?.btnThree?.setOnClickListener {
            if (!isOperandClicked) {
                numberOne += binding?.btnThree?.text ?: ""
                binding?.input?.text = numberOne.toString()
            } else {
                numberTwo += binding?.btnThree?.text ?: ""
                binding?.input?.text = numberTwo.toString()
            }
        }
        binding?.btnFour?.setOnClickListener {
            if (!isOperandClicked) {
                numberOne += binding?.btnFour?.text ?: ""
                binding?.input?.text = numberOne.toString()
            } else {
                numberTwo += binding?.btnFour?.text ?: ""
                binding?.input?.text = numberTwo.toString()
            }
        }
        binding?.btnFive?.setOnClickListener {
            if (!isOperandClicked) {
                numberOne += binding?.btnFive?.text ?: ""
                binding?.input?.text = numberOne.toString()
            } else {
                numberTwo += binding?.btnFive?.text ?: ""
                binding?.input?.text = numberTwo.toString()
            }
        }
        binding?.btnSix?.setOnClickListener {
            if (!isOperandClicked) {
                numberOne += binding?.btnSix?.text ?: ""
                binding?.input?.text = numberOne.toString()
            } else {
                numberTwo += binding?.btnSix?.text ?: ""
                binding?.input?.text = numberTwo.toString()
            }
        }
        binding?.btnSeven?.setOnClickListener {
            if (!isOperandClicked) {
                numberOne += binding?.btnSeven?.text ?: ""
                binding?.input?.text = numberOne.toString()
            } else {
                numberTwo += binding?.btnSeven?.text ?: ""
                binding?.input?.text = numberTwo.toString()
            }
        }
        binding?.btnEight?.setOnClickListener {
            if (!isOperandClicked) {
                numberOne += binding?.btnEight?.text ?: ""
                binding?.input?.text = numberOne.toString()
            } else {
                numberTwo += binding?.btnEight?.text ?: ""
                binding?.input?.text = numberTwo.toString()
            }
        }
        binding?.btnNine?.setOnClickListener {
            if (!isOperandClicked) {
                numberOne += binding?.btnNine?.text ?: ""
                binding?.input?.text = numberOne.toString()
            } else {
                numberTwo += binding?.btnNine?.text ?: ""
                binding?.input?.text = numberTwo.toString()
            }
        }
        binding?.btnZero?.setOnClickListener {
            if (!isOperandClicked) {
                numberOne += binding?.btnZero?.text ?: ""
                binding?.input?.text = numberOne.toString()
            } else {
                numberTwo += binding?.btnZero?.text ?: ""
                binding?.input?.text = numberTwo.toString()
            }
        }
        binding?.btnDot?.setOnClickListener {
            if (!isOperandClicked) {
                numberOne += binding?.btnDot?.text ?: ""
                binding?.input?.text = numberOne.toString()
            } else {
                numberTwo += binding?.btnDot?.text ?: ""
                binding?.input?.text = numberTwo.toString()
            }
        }
        binding?.sum?.setOnClickListener {
            isOperandClicked = true
            operandType = OperandsType.PLUS
            binding?.input?.text = ""
            if (isOperandClicked && numberOne.isNotEmpty() && numberTwo.isNotEmpty()) {
                val num1 = numberOne.toDoubleOrNull() ?: 0.0
                val num2 = numberTwo.toDoubleOrNull() ?: 0.0
                val result = when (operandType) {
                    OperandsType.PLUS -> num1 + num2
                    OperandsType.MINUS -> num1 - num2
                    OperandsType.DIVIDE -> num1 / num2
                    OperandsType.MULTIPLY -> num1 * num2
                    OperandsType.POWER -> Math.pow(num1, num2)
                    OperandsType.PERCENT -> num1 * num2 / 100
                    else -> 0.0
                }
                numberOne = result.toString()
                numberTwo = ""
                binding?.input?.text = numberOne
                isOperandClicked = true
            }

        }
        binding?.sub?.setOnClickListener {
            isOperandClicked = true
            operandType = OperandsType.MINUS
            binding?.input?.text = ""
        }
        binding?.div?.setOnClickListener {
            isOperandClicked = true
            operandType = OperandsType.DIVIDE
            binding?.input?.text = ""
        }
        binding?.mul?.setOnClickListener {
            isOperandClicked = true
            operandType = OperandsType.MULTIPLY
            binding?.input?.text = ""
        }
        binding?.btnPercent?.setOnClickListener {
            isOperandClicked = true
            operandType = OperandsType.PERCENT
            binding?.input?.text = ""
        }
        binding?.btnPower?.setOnClickListener {
            isOperandClicked = true
            operandType = OperandsType.POWER
            binding?.input?.text = ""
        }
        binding?.btnCalculate?.setOnClickListener {
            val num: Double = numberOne.toDoubleOrNull() ?: 0.0
            val numTwo: Double = numberTwo.toDoubleOrNull() ?: 0.0
            val result = when (operandType) {
                OperandsType.MINUS -> {
                    num - numTwo
                }

                OperandsType.PLUS -> {
                    num + numTwo
                }

                OperandsType.DIVIDE -> {
                    num / numTwo
                }

                OperandsType.MULTIPLY -> {
                    num * numTwo
                }

                OperandsType.POWER -> {
                    Math.pow(num.toDouble(), numTwo.toDouble())
                }

                OperandsType.PERCENT -> {
                    num.toDouble() * numTwo.toDouble() / 100
                }

                else -> {
                    0
                }
            }
            binding?.output?.text = result.toString()
        }
    }

//    private fun clickHandler() {
//
//        binding?.btnZero?.setOnClickListener {
//            startActivity(Intent(this, FormActivity::class.java).apply {
//                putExtra("name", "Manish")
//                putExtra("age", 10)
//            })
//        }

    /*        binding?.eq?.setOnClickListener {

            }

            binding?.b0?.setOnClickListener {

            }*/
}


