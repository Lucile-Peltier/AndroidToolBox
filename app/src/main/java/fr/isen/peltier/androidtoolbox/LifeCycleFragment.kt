package fr.isen.peltier.androidtoolbox


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_life_cycle.*

/**
 * A simple [Fragment] subclass.
 */
class LifeCycleFragment : Fragment() {

    val TAG = "State Change"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_life_cycle, container, false)
    }

    override fun onStart() {
        super.onStart()
        fragment.text = "fragment onStart"
    }

    override fun onResume() {
        super.onResume()
        fragment.text = fragment.text.toString() + ", fragment onResume"
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "fragment onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "fragment onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        // Toast.makeText(this, "fragment onDestroy", Toast.LENGTH_LONG).show()
        Log.i(TAG, "fragment onDestroy")
    }


}
