package fr.isen.peltier.androidtoolbox

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    val goodIdentifier = "admin"
    var goodPassword = "123"
    var userPref: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        userPref = getSharedPreferences(Constants.UserPreferenceName, Context.MODE_PRIVATE)
        checkPreferences()

        //Pour afficher l'identifiant entré par l'utilisateur lors du click.
       // validateButton.setOnClickListener {
        //    Toast.makeText(this@LoginActivity, " Bonjour, ${username.text}", Toast.LENGTH_LONG).show()
       // }

        validateButton.setOnClickListener {
            doLogin()
        }
    }

    fun doLogin() {
        if (canLog(username.text.toString(), password.text.toString())) {
            saveUser(username.text.toString(), password.text.toString())
            val intent = Intent( this, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }
    }

    fun canLog(identifier: String, password: String): Boolean {
        return identifier == goodIdentifier && password == goodPassword
    }

    fun saveUser(identifier: String, password: String) {
        val editor = userPref?.edit()
        editor?.putString(Constants.kIdentifier, identifier)
        editor?.putString(Constants.kPassword, password)
        editor?.apply()
    }

    fun checkPreferences() {
        val identifier = userPref?.getString(Constants.kIdentifier, null) ?: "" //On donne une valeur par défault pour que ce soit pas nul
        val password = userPref?.getString(Constants.kPassword, null) ?: ""
        username.setText(identifier)
        this.password.setText(password)
        doLogin()
    }
}
