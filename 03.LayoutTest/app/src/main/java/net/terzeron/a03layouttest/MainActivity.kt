package net.terzeron.a03layouttest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        radioGroupABC.setOnCheckedChangeListener({
            radioGroup, i -> selectedRadio.setText(findViewById<RadioButton>(radioGroup.checkedRadioButtonId).text)
        })
        initButton.setOnClickListener({
            view -> radioA.isChecked = true
        })
    }
}
