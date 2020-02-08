package fr.isen.peltier.androidtoolbox

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import fr.isen.peltier.androidtoolbox.Model.UserModel
import kotlinx.android.synthetic.main.activity_web_contact.*
import kotlinx.android.synthetic.main.activity_web_contact.view.*
import java.io.Serializable


class WebContactActivity: AppCompatActivity(), Serializable {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_contact)
        getUser()
    }

    fun getUser() {
        val view: View = findViewById (R.id.imageContactViewWebServices)
        var currentContact =  intent.getSerializableExtra("${getString(R.string.user_chosen)}") as UserModel

        nameContactField.text ="${currentContact.name?.title}. ${currentContact.name?.first} ${currentContact.name?.last}"
        emailContactField.text = currentContact.email
        countryContactField.text = currentContact.location?.country
        stateContactField.text = currentContact.location?.state
        cityContactField.text = currentContact.location?.city
        streetContactField.text = currentContact.location?.street?.name
        Picasso
            .with(this)
            .load(currentContact.picture?.large)
            .into(view.imageContactViewWebServices)
    }
}
