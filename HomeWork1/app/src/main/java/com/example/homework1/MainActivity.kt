package com.example.homework1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val input1 = findViewById<EditText>(R.id.input1)
        val input2 = findViewById<EditText>(R.id.input2)
        val sumButton = findViewById<Button>(R.id.sumButton)
        val subButton = findViewById<Button>(R.id.subButton)
        val multiplyButton = findViewById<Button>(R.id.multiplyButton)
        val divideButton = findViewById<Button>(R.id.divideButton)
        sumButton.setOnClickListener {
            passToSecondActivity("sum", input1, input2)
        }
        subButton.setOnClickListener {
            passToSecondActivity("subtract", input1, input2)
        }
        multiplyButton.setOnClickListener {
            passToSecondActivity("multiply", input1, input2)
        }
        divideButton.setOnClickListener {
            passToSecondActivity("divide", input1, input2)
        }

    }
    private fun sum(input1: EditText, input2: EditText): String {
        val result = input1.run { text.toString().toInt() } + input2.run { text.toString().toInt() }
        return "${input1.text} + ${input2.text} = $result"
    }
    private fun subtract(input1: EditText, input2: EditText): String {
        val result = input1.run { text.toString().toInt() } - input2.run { text.toString().toInt()}
        return "${input1.text} - ${input2.text} = $result"
    }
    private fun multiply(input1: EditText, input2: EditText): String  {
        val result = input1.run { text.toString().toInt() } * input2.run { text.toString().toInt()}
        return "${input1.text} * ${input2.text} = $result"
    }
    private fun divide(input1: EditText, input2: EditText): String {
        return try {
            if (input2.run { text.toString().toInt() } == 0) {
                val exception: ArithmeticException = ArithmeticException("Can't Divide By Zero")
                throw exception
            }
            val result = input1.run { text.toString().toFloat() } / input2.run { text.toString().toFloat()}
            "${input1.text} / ${input2.text} = $result"
        } catch (e: ArithmeticException) {
            "Can't divide by zero"
        }
    }
    private fun passToSecondActivity(operand: String = "divide", input1: EditText, input2: EditText){
        try {
            if(input1.text.isEmpty() || input2.text.isEmpty()){
                val emptyInputException = EmptyInputException("Empty Input")
                throw emptyInputException
            }
            val intent = Intent(this@MainActivity, SecondActivity::class.java)
            val result: Any = when (operand) {
                "sum" -> sum(input1, input2)
                "subtract" -> subtract(input1, input2)
                "multiply" -> multiply(input1, input2)
                else -> divide(input1, input2)
            }
            intent.putExtra("result", "$result")
            startActivity(intent)
        } catch (e: EmptyInputException) {
            Toast.makeText(this,"Please fill both inputs",Toast.LENGTH_LONG).show()
        }
    }
}
