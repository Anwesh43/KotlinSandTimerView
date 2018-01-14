package ui.anwesome.com.kotlinsandtimerview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import ui.anwesome.com.sandtimerview.SandTimerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SandTimerView.create(this)
    }
}
