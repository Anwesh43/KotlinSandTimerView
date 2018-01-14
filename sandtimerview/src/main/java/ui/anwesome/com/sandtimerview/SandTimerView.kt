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
        val state = SandTimerState()
        fun draw(canvas:Canvas,paint:Paint) {
            canvas.save()
            canvas.translate(x,y)
            canvas.rotate(180f*state.scales[0])
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
                val scale = state.scales[1]
                val sf = 1-scale + i*(2*scale - 1)
                val path2 = Path()
                path2.moveTo(0f,0f)
                path2.lineTo(-size/2, size/2*sf)
                path2.lineTo(size/2, size/2*sf)
                path2.lineTo(0f,0f)
                canvas.drawPath(path2,paint)
                canvas.restore()
            }
            canvas.restore()
        }
        fun update(stopcb:()->Unit) {
            state.update(stopcb)
        }
        fun startUpdating(startcb:()->Unit) {
            state.startUpdating(startcb)
        }
    }
    data class SandTimerState(var j:Int = 0,var dir:Int = 0) {
        var scales:Array<Float> = arrayOf(0f,0f)
        fun update(stopcb:()->Unit) {
            scales[j]+=0.1f
            if(scales[j] > 1) {
                scales[j] = 1f
                j++
                if(j == scales.size) {
                    for(i in 0..scales.size-1) {
                        scales[i] = 0f
                    }
                    dir = 0
                    stopcb()
                }
            }
        }
        fun startUpdating(startcb:()->Unit) {
            if(dir == 0) {
                dir = 1
                startcb()
            }
        }
    }
    class Animator(var view:View,var animated:Boolean = false) {
        fun animate(updatecb:()->Unit) {
            if(animated) {
                updatecb()
                try {
                    Thread.sleep(50)
                    view.invalidate()
                }
                catch(ex:Exception) {

                }
            }
        }
        fun stop() {
            if(animated) {
                animated = false
            }
        }
        fun start() {
            if(!animated) {
                animated = true
                view.postInvalidate()
            }
        }
    }
    class Renderer(var view:SandTimerView,var time:Int = 0) {
        val animator = Animator(view)
        var sandTimer:SandTimer?=null
        fun render(canvas:Canvas,paint:Paint) {
            if(time == 0) {
                val w = canvas.width.toFloat()
                val h = canvas.height.toFloat()
                sandTimer = SandTimer(w/2,h/2,Math.min(w,h)/5)
            }
            sandTimer?.draw(canvas,paint)
            time++
            animator.animate {
                sandTimer?.update {
                    animator.stop()
                }
            }
        }
        fun handleTap() {
            sandTimer?.startUpdating {
                animator.start()
            }
        }
    }
}