package com.example.myexchanger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.floor

class MainActivity : AppCompatActivity() {

    val Rates = mapOf<String, Double>("RUB" to 1.0, "USD" to 74.0, "EUR" to 78.56, "GBP" to 87.13, "CHF" to 72.85,
                                      "UAH" to 2.58, "KZT" to 0.17, "CNY" to 9.7, "BYN" to 28.86, "JPY" to 0.63)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val curr = resources.getStringArray(R.array.currency)
        val spin1 = findViewById<Spinner>(R.id.Value1)
        val spin2 = findViewById<Spinner>(R.id.Value2)
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, curr)

        spin1.adapter = arrayAdapter
        spin2.adapter = arrayAdapter

        spin1.onItemSelectedListener = object :

            AdapterView.OnItemSelectedListener{

            override fun onNothingSelected(parent: AdapterView<*>?) {
                nameValue1.text = "RUB"
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                nameValue1.text = curr[position]
            }

        }
        spin2.onItemSelectedListener = object :

            AdapterView.OnItemSelectedListener{

            override fun onNothingSelected(parent: AdapterView<*>?) {
                nameValue2.text = "USD"
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                nameValue2.text = curr[position]
            }

        }


        btn_0.setOnClickListener{ setText("0") }
        btn_1.setOnClickListener{ setText("1") }
        btn_2.setOnClickListener{ setText("2") }
        btn_3.setOnClickListener{ setText("3") }
        btn_4.setOnClickListener{ setText("4") }
        btn_5.setOnClickListener{ setText("5") }
        btn_6.setOnClickListener{ setText("6") }
        btn_7.setOnClickListener{ setText("7") }
        btn_8.setOnClickListener{ setText("8") }
        btn_9.setOnClickListener{ setText("9") }

        btn_dot.setOnClickListener{
            if(fsValue.text == "")
                setText("0.")
            else if("." !in fsValue.text)
                setText(".")
        }

        btn_back.setOnClickListener{
            if(fsValue.text != ""){
                val t = fsValue.text.toString()
                fsValue.text = t.substring(0, t.length - 1)

                if(fsValue.text.isNotEmpty() && scValue.text.isNotEmpty()) exchange()
                else scValue.text = ""
            }
        }
        btn_AC.setOnClickListener{
            fsValue.text = ""
            scValue.text = ""
        }

        btn_rev.setOnClickListener{
            val t = nameValue1.text
            nameValue1.text = nameValue2.text
            nameValue2.text = t

            if(fsValue.text.isNotEmpty()) exchange()

        }

        btn_Exc.setOnClickListener{ if(fsValue.text.isNotEmpty()) exchange() }

    }

    fun setText(str: String){
        fsValue.append(str)
    }

    fun exchange(){
        val a = Rates[nameValue1.text]
        val b = Rates[nameValue2.text]

        var R: Double? = fsValue.text.toString().toDouble()
        if (!(R == null || a == null || b == null)) {
            R *= a / b
            R *= 100
            R = floor(R)
            R /= 100
        }

        scValue.text = R.toString()
    }

}





