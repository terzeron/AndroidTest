package net.terzeron.a05layouttest

import android.graphics.Color
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.v7.app.AppCompatActivity
import android.util.TypedValue
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configureLayout()
    }

    private fun converToPx(value: Int): Int {
        val r = resources
        val px = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, value.toFloat(),
                r.displayMetrics).toInt()
        return px
    }

    private fun configureLayout() {
        // 뷰를 만들고
        val myButton = Button(this)
        myButton.text = getString(R.string.press_me)
        myButton.setBackgroundColor(Color.YELLOW)
        myButton.id = R.id.myButton

        // 뷰를 만들고
        val myEditText = EditText(this)
        myEditText.id = R.id.myEditText
        myEditText.width = converToPx(200)

        // 레이아웃을 만들고
        val myLayout = ConstraintLayout(this)
        myLayout.setBackgroundColor(Color.BLUE)

        // 레이아웃에 뷰를 추가
        myLayout.addView(myButton)
        myLayout.addView(myEditText)
        setContentView(myLayout)

        // constraintset을 지정함
        val set = ConstraintSet()
        set.constrainHeight(myButton.id, ConstraintSet.WRAP_CONTENT)
        set.constrainWidth(myButton.id, ConstraintSet.WRAP_CONTENT)
        set.connect(myButton.id, ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 0)
        set.connect(myButton.id, ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 0)
        set.connect(myButton.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0)
        set.connect(myButton.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0)

        set.constrainHeight(myEditText.id, ConstraintSet.WRAP_CONTENT)
        set.constrainWidth(myEditText.id, ConstraintSet.WRAP_CONTENT)
        set.connect(myEditText.id, ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 0)
        set.connect(myEditText.id, ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 0)
        set.connect(myEditText.id, ConstraintSet.BOTTOM, myButton.id, ConstraintSet.TOP, 70)

        set.applyTo(myLayout)
    }

}
