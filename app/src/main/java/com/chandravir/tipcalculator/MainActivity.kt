package com.chandravir.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView

private const val TAG = "MainActivity"
private const val INITIAL_TIP_PERCENT = 15

class MainActivity : AppCompatActivity() {

    private lateinit var etBase: EditText
    private lateinit var txtPercentage: TextView
    private lateinit var seekBar: SeekBar
    private lateinit var txtTipAmount: TextView
    private lateinit var txtTotalAmount: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etBase = findViewById(R.id.etBill)
        txtPercentage = findViewById(R.id.txtPercentage)
        seekBar = findViewById(R.id.seekbar)
        txtTipAmount = findViewById(R.id.txtTipAmount)
        txtTotalAmount = findViewById(R.id.txtTotalAmount)

        seekBar.progress = INITIAL_TIP_PERCENT
        txtPercentage.text = "$INITIAL_TIP_PERCENT%"
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                Log.i(TAG, "onProgressedChanged $progress")
                txtPercentage.text = "$progress%"
                computeTipAndCalculate()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })

        etBase.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                Log.i(TAG, "afterTextChanged $s")
                computeTipAndCalculate()
            }
        })





    }

    private fun computeTipAndCalculate() {
        //Get the value of the base and tip percent
        if(etBase.text.isEmpty()){
            txtTipAmount.text = ""
            txtTotalAmount.text = ""
            return
        }
        val baseAmount = etBase.text.toString().toDouble()
        val tipPercent = seekBar.progress
        val tipAmount = baseAmount * tipPercent / 100
        val totalAmount = baseAmount + tipAmount
        txtTipAmount.text = "%.2f".format(tipAmount)
        txtTotalAmount.text = "%.2f".format(totalAmount)
    }
}