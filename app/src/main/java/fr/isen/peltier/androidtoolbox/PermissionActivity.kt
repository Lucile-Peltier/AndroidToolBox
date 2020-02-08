package fr.isen.peltier.androidtoolbox

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import fr.isen.peltier.androidtoolbox.Model.ContactsAdapter
import fr.isen.peltier.androidtoolbox.Model.ContactModel
import kotlinx.android.synthetic.main.activity_permission.*

class PermissionActivity : AppCompatActivity(), LocationListener {

    lateinit var locationManager: LocationManager

    companion object {
        val pictureRequestCode = 1
        val contactPermissionRequestCode = 2
        val gpsPermissionRequestCode = 3
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permission)

        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        galleryButton.setOnClickListener {
            onChangePhoto()
        }

        requestPermission(android.Manifest.permission.READ_CONTACTS, contactPermissionRequestCode) {
            readContacts()
        }

        requestPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION, gpsPermissionRequestCode) {
            startGPS()
        }
    }

    fun onChangePhoto() {
        val galleryIntent = Intent(Intent.ACTION_PICK)
        galleryIntent.type = "image/*"

        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        val intentChooser = Intent.createChooser(galleryIntent, getString(R.string.intentChooser_title))
        intentChooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(cameraIntent))
        startActivityForResult(intentChooser, PermissionActivity.pictureRequestCode)
    }

    fun readContacts() {
        val contactList = ArrayList<ContactModel>()
        val contacts = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null)
        while(contacts?.moveToNext() ?: false) {
            val displayName = contacts?.getString(contacts.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
            val contactModel =
                ContactModel()
            contactModel.displayName = displayName.toString()
            contactList.add(contactModel)
        }
        contactRecycleView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        contactRecycleView.adapter =
            ContactsAdapter(contactList)
    }

    @SuppressLint("MissingPermission")
    fun startGPS() {
        locationManager.requestSingleUpdate(LocationManager.NETWORK_PROVIDER, this, null)
        val location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
        location?.let {
            refreshPositionUI(it)
        }
    }

    fun refreshPositionUI(location: Location) {
        locationTextView.text = "${R.string.location_lat} : ${location.latitude} \n${R.string.location_long} : ${location.longitude}"
    }

    fun requestPermission(permissionToRequest: String, requestCode: Int, handler: ()-> Unit) {
        if(ContextCompat.checkSelfPermission(this, permissionToRequest) != PackageManager.PERMISSION_GRANTED) {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, permissionToRequest)) {
                Toast.makeText(this,"Changer les permissions",Toast.LENGTH_LONG).show()
            } else {
                ActivityCompat.requestPermissions(this, arrayOf(permissionToRequest), requestCode)
            }
        } else {
            handler()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == contactPermissionRequestCode){
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                readContacts()
            } else {
                Toast.makeText(this,"Permission refusée sur les contacts",Toast.LENGTH_LONG).show()
            }
        }
        if(requestCode == gpsPermissionRequestCode){
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                startGPS()
            } else {
                Toast.makeText(this,"Permission refusée sur le GPS",Toast.LENGTH_LONG).show()
            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PermissionActivity.pictureRequestCode && resultCode == Activity.RESULT_OK) {
            if (data?.data != null) {
                galleryButton.setImageURI(data.data)
            }
            else {
                val bitmap = data?.extras?.get("data") as? Bitmap
                bitmap?.let {
                    galleryButton.setImageBitmap(it)
                }
            }
        }
    }

    override fun onLocationChanged(location: Location?) {
        location?.let {
            refreshPositionUI(it)
        }
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}

    override fun onProviderEnabled(provider: String?) {}

    override fun onProviderDisabled(provider: String?) {}
}
