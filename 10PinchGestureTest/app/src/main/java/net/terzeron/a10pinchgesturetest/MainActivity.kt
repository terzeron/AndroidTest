package net.terzeron.a10pinchgesturetest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var scaleGestureDetector: ScaleGestureDetector? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        scaleGestureDetector = ScaleGestureDetector(this, MyOnScaleGestureListener())
    }
    override fun onTouchEvent(event: MotionEvent): Boolean {
        scaleGestureDetector?.onTouchEvent(event)
        return true
    }
    inner class MyOnScaleGestureListener: ScaleGestureDetector.SimpleOnScaleGestureListener() {
        override fun onScale(detector: ScaleGestureDetector): Boolean {
            val scaleFactor = detector.scaleFactor
            if (scaleFactor > 1) {
                myTextView.text = "Zooming out"
            } else {
                myTextView.text = "Zooming in"
            }
            return true
        }
        override fun onScaleBegin(detector: ScaleGestureDetector): Boolean {
            return true
        }
        override fun onScaleEnd(detector: ScaleGestureDetector?) {
        }
    }
}
