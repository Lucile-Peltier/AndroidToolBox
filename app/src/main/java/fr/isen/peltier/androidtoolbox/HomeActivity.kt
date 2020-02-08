package fr.isen.peltier.androidtoolbox

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.isen.peltier.androidtoolbox.Classes.Constants
import fr.isen.peltier.androidtoolbox.Classes.CycleVie
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        cycleButton.setOnClickListener {
            goCycle()
        }

        saveButton.setOnClickListener {
            saveInfo()
        }

        logOutButton.setOnClickListener {
            val userPref = getSharedPreferences(Constants.UserPreferenceName, Context.MODE_PRIVATE)
            val editor = userPref.edit()
            editor.clear() //vide tous les chants
            editor.apply() //met plus de temps que le commit à appliquer les changements.
            val intent = Intent( this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish() //force la fin de l'activité fermée
        }

        permissionsButton.setOnClickListener {
            val intent = Intent( this, PermissionActivity::class.java)
            startActivity(intent)
        }

        webServicesButton.setOnClickListener {
            val intent = Intent( this, WebServicesActivity::class.java)
            startActivity(intent)
        }

    }

    fun goCycle () {
        val intent = Intent( this, CycleVie::class.java)
        startActivity(intent)
    }

    fun saveInfo() {
        val intent = Intent( this, FormActivity::class.java)
        startActivity(intent)
    }
}
