package fr.isen.peltier.androidtoolbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_cycle_vie.*

class CycleVie : AppCompatActivity(), OnFragmentInteractionListener {

    val TAG = "State Change"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cycle_vie)
        Log.i(TAG, "onCreate")
        val firstFragment = FirstFragment()
        supportFragmentManager.beginTransaction().replace(R.id.blankFragment, firstFragment).commit()
    }

    override fun swipeFragment() {
        val secondFragment = SecondFragment()
        supportFragmentManager.beginTransaction().replace(R.id.blankFragment, secondFragment).commit()
    }

    override fun onStart() {
        super.onStart()
        activityState.text = activityState.text.toString() + ", onStart"
    }

    override fun onResume() {
        super.onResume()
        activityState.text = activityState.text.toString() + ", onResume"
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "onStop")
    }

    override fun onRestart() {
        super.onRestart()
        activityState.text = activityState.text.toString() + ", onRestart"
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "onDestroy", Toast.LENGTH_LONG).show()
    }
}
