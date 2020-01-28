package fr.isen.peltier.androidtoolbox

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_form.*
import org.json.JSONObject
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class FormActivity : AppCompatActivity() {

    var currentDate = Date()

    companion object {
        val kFirstNameKey = "kFirstNameKey"
        val kLastNameKey = "kLastNameKey"
        val kBirthday = "kBirthday"
        val kFilename = "data.json"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        saveFormButton.setOnClickListener {
            save()
        }

        readButton.setOnClickListener {
            read()
        }

        dateEditText.setOnFocusChangeListener { view, hasFocus ->
            if(hasFocus) {
                dateEditText.clearFocus()
                val dialog = DatePickerDialog(this,
                    DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                        onDateChoose(year, month, dayOfMonth)
                    },
                    1990,
                    11,
                    25)
                dialog.show()
            }
        }
    }

    fun save() {
        if (firstNameField.text.toString().isNotEmpty() &&
            nameField.text.toString().isNotEmpty() &&
            dateEditText.text.toString().isNotEmpty()) {
            val json = JSONObject()
            json.put(FormActivity.kBirthday, dateEditText.text.toString())
            json.put(FormActivity.kLastNameKey, nameField.text.toString())
            json.put(FormActivity.kFirstNameKey, firstNameField.text.toString())
            Toast.makeText(this, json.toString(), Toast.LENGTH_LONG).show()

            val file = File(cacheDir.absolutePath+"/"+FormActivity.kFilename)
            file.writeText(json.toString())
        }
        else {
            Toast.makeText(this, R.string.fields_empty, Toast.LENGTH_LONG).show()
        }
    }

    fun read() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.user_info)

        val file = File(cacheDir.absolutePath+"/"+FormActivity.kFilename)
        val readString = file.readText()
        val json = JSONObject(readString)
        val dateString = json.getString(FormActivity.kBirthday)
        val components = dateString.split("/")

        builder.setMessage("${getString(R.string.last_name)} : ${json.getString(FormActivity.kLastNameKey)} \n" +
                "${getString(R.string.first_name)} : ${json.getString(FormActivity.kFirstNameKey)} \n" +
                "${getString(R.string.your_age)} : ${getAge(components[2].toInt(), components[1].toInt(), components[0].toInt())}")

        builder.setNeutralButton(R.string.pop_up_button){_,_ -> }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    fun onDateChoose(year:Int, month: Int, day: Int) {
        dateEditText.setText(String.format("%02d/%02d/%04d", day, month+1, year))
    }

    fun getAge(year: Int, month: Int, day:Int): Int {
        val formatter = SimpleDateFormat("dd/MM/yyyy")
        val dateString = formatter.format(currentDate)
        val components = dateString.split("/")

        var age = components[2].toInt() -year
        if (components[1].toInt() < month) {
            age--
        }
        else if (components[1].toInt() == month && components[0].toInt() < day) {
            age--
        }
        return age
    }
}
