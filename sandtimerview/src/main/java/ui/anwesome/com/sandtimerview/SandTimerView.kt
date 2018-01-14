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
    data class SandTimer(var x:Float,var y:Float,var size:Float) {
        fun draw(canvas:Canvas,paint:Paint) {
            canvas.save()
            canvas.translate(x,y)
            canvas.rotate(180f)
            for(i in 0..1) {
                canvas.save()
                canvas.scale(1f,1f-2*i)
                paint.style = Paint.Style.STROKE
                paint.strokeWidth = size/12
                paint.strokeCap = Paint.Cap.ROUND
                val path = Path()
                path.moveTo(0f,0f)
                path.lineTo(-size/2, size/2)
                path.lineTo(size/2, size/2)
                path.lineTo(0f,0f)
                canvas.drawPath(path,paint)
                paint.style = Paint.Style.FILL
                val path2 = Path()
                path2.moveTo(0f,0f)
                path2.lineTo(-size/2, size/2)
                path2.lineTo(size/2, size/2)
                path2.lineTo(0f,0f)
                canvas.drawPath(path2,paint)
                canvas.restore()
            }
            canvas.restore()
        }
        fun update(stopcb:(Float)->Unit) {

        }
        fun startUpdating(startcb:()->Unit) {

        }
    }
}