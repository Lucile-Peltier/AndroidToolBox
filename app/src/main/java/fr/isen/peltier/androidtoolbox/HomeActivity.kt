package fr.isen.peltier.androidtoolbox

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        cycleButton.setOnClickListener {
            goCycle()
        }
    }

    fun goCycle () {
        val intent = Intent( this, CycleVie::class.java)
        startActivity(intent)
    }
}
