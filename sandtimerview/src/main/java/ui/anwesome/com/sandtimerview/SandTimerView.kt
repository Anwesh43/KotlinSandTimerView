package ui.anwesome.com.sandtimerview

/**
 * Created by anweshmishra on 15/01/18.
 */
import android.view.*
import android.graphics.*
import android.content.*
class SandTimerView(ctx:Context):View(ctx) {
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    override fun onDraw(canvas:Canvas) {

    }
    override fun onTouchEvent(event:MotionEvent):Boolean {
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {

            }
        }
        return true
    }
}