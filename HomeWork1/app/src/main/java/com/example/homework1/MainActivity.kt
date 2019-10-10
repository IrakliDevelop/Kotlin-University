package com.example.homework1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sumButton = findViewById<Button>(R.id.sumButton)
        val subButton = findViewById<Button>(R.id.subButton)
        val multiplyButton = findViewById<Button>(R.id.multiplyButton)
        val divideButton = findViewById<Button>(R.id.divideButton)
        sumButton.setOnClickListener {
            passToSecondActivity("sum")
        }
        subButton.setOnClickListener {
            passToSecondActivity("subtract")
        }
        multiplyButton.setOnClickListener {
            passToSecondActivity("multiply")
        }
        divideButton.setOnClickListener {
            passToSecondActivity()
        }

    }
    private fun calculate(): Int {
        val input1 = findViewById<EditText>(R.id.input1)
        val input2 = findViewById<EditText>(R.id.input2)
        return input1.run { text.toString().toInt() } + input2.run { text.toString().toInt() }
    }
    private fun subtract(): Int {
        val input1 = findViewById<EditText>(R.id.input1)
        val input2 = findViewById<EditText>(R.id.input2)
        return input1.run { text.toString().toInt() } - input2.run { text.toString().toInt()}
    }
    private fun multiply(): Int  {
        val input1 = findViewById<EditText>(R.id.input1)
        val input2 = findViewById<EditText>(R.id.input2)
        return input1.run { text.toString().toInt() } * input2.run { text.toString().toInt()}
    }
    private fun divide(): Any {
        val input1 = findViewById<EditText>(R.id.input1)
        val input2 = findViewById<EditText>(R.id.input2)
        return try {
            input1.run { text.toString().toFloat() } / input2.run { text.toString().toFloat()}
        } catch (e: ArithmeticException) {
            "Can't divide by zero"
        }
    }
    private fun passToSecondActivity(operand: String = "divide"){
        val intent = Intent(this@MainActivity, SecondActivity::class.java)
        var result: Any
        when (operand) {
            "sum" -> result = calculate()
            "subtract" -> result = subtract()
            "multiply" -> result = multiply()
            else -> result = divide()
        }
        intent.putExtra("result", "$result")
        startActivity(intent)
    }
}
