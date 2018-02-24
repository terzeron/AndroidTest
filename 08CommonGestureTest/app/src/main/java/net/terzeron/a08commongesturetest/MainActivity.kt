package net.terzeron.a08commongesturetest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onShowPress(e: MotionEvent?) {
        gestureStatusText.text = "onShowPress"
    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean {
        gestureStatusText.text = "onSingleTapUp"
        return true
    }

    override fun onDown(e: MotionEvent?): Boolean {
        gestureStatusText.text = "onDown"
        return true
    }

    override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
        gestureStatusText.text = "onFling"
        return true
    }

    override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
        gestureStatusText.text = "onScroll"
        return true
    }

    override fun onLongPress(e: MotionEvent?) {
        gestureStatusText.text = "onLongPress"
    }

    override fun onDoubleTap(e: MotionEvent?): Boolean {
        gestureStatusText.text = "onDoubleTap"
        return true
    }

    override fun onDoubleTapEvent(e: MotionEvent?): Boolean {
        gestureStatusText.text = "onDoubleTapEvent"
        return true
    }

    override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
        gestureStatusText.text = "onSingleTapConfirmed"
        return true
    }
}
