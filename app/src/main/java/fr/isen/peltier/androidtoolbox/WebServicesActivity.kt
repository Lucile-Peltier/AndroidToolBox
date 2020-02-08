package fr.isen.peltier.androidtoolbox

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.peltier.androidtoolbox.Model.WebServicesAdapter
import fr.isen.peltier.androidtoolbox.Model.RamdomUsersResult
import fr.isen.peltier.androidtoolbox.Model.UserModel
import kotlinx.android.synthetic.main.activity_web_services.*
import java.lang.reflect.Array.get

class WebServicesActivity : AppCompatActivity(), WebServicesAdapter.OnUserRecyclerViewListener {

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
                userRecycleView.adapter =
                    WebServicesAdapter(it, this)
            }
        },
            Response.ErrorListener { Toast.makeText(this, "${getString(R.string.error_text)}", Toast.LENGTH_LONG).show() })
        queue.add(req)
    }

    override fun onSelectUser(user: UserModel){
        val intent = Intent(this, WebContactActivity::class.java)
        intent.putExtra("${getString(R.string.user_chosen)}", user)
        startActivity(intent)
    }
}
