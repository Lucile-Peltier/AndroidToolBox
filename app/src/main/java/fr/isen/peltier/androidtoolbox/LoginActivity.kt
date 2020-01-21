package fr.isen.peltier.androidtoolbox

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    val goodIdentifier = "admin"
    var goodPassword = "123"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

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
            val intent = Intent( this, HomeActivity::class.java)
            startActivity(intent)
        }
    }

    fun canLog(identifier: String, password: String): Boolean {
        return identifier == goodIdentifier && password == goodPassword
    }
}
