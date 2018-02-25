package net.terzeron.a08commongesturetest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.GestureDetectorCompat
import android.view.GestureDetector
import android.view.MotionEvent
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),
        GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    var gDetector: GestureDetectorCompat? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.gDetector = GestureDetectorCompat(this, this)
        gDetector?.setOnDoubleTapListener(this)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        this.gDetector?.onTouchEvent(event)
        return super.onTouchEvent(event)
    }

    override fun onShowPress(e: MotionEvent?) {
        gestureStatusText.text = "onShowPress: " + e
    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean {
        gestureStatusText.text = "onSingleTapUp: " + e
        return true
    }

    override fun onDown(e: MotionEvent?): Boolean {
        gestureStatusText.text = "onDown: " + e
        return true
    }

    override fun onFling(e1: MotionEvent?, e2: MotionEvent?,
                         velocityX: Float, velocityY: Float): Boolean {
        gestureStatusText.text = "onFling: " + e1 + " " + e2 + " " +
                velocityX + " " + velocityY
        return true
    }

    override fun onScroll(e1: MotionEvent?, e2: MotionEvent?,
                          distanceX: Float, distanceY: Float): Boolean {
        gestureStatusText.text = "onScroll: " + e1 + " " + e2 + " " +
                distanceX + " " + distanceX
        return true
    }

    override fun onLongPress(e: MotionEvent?) {
        gestureStatusText.text = "onLongPress: " + e
    }

    override fun onDoubleTap(e: MotionEvent?): Boolean {
        gestureStatusText.text = "onDoubleTap: " + e
        return true
    }

    override fun onDoubleTapEvent(e: MotionEvent?): Boolean {
        gestureStatusText.text = "onDoubleTapEvent: " + e
        return true
    }

    override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
        gestureStatusText.text = "onSingleTapConfirmed: " + e
        return true
    }
}
