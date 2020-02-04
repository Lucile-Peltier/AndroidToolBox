package fr.isen.peltier.androidtoolbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.peltier.androidtoolbox.Model.RamdomUsersResult
import kotlinx.android.synthetic.main.activity_web_services.*

class WebServicesActivity : AppCompatActivity() {

    var textView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_services)

        getUsers()
    }

    fun getUsers() {
        val userAPI = "${getString(R.string.url_web_service)}"
        val queue = Volley.newRequestQueue(this)

        val req = StringRequest(Request.Method.GET, userAPI, Response.Listener<String> {
            val gson = Gson()
            val result = gson.fromJson(it, RamdomUsersResult::class.java)

            userRecycleView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            result.results?.let {
                userRecycleView.adapter = WebServicesAdapter(it)
            }
        },
            Response.ErrorListener { Toast.makeText(this, "${getString(R.string.error_text)}", Toast.LENGTH_LONG).show() })
        queue.add(req)
    }
}
