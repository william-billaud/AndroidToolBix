package fr.isen.billaud.androidtoolbox

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Criteria
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import fr.isen.billaud.androidtoolbox.adapter.ContactListViewAdapter
import kotlinx.android.synthetic.main.activity_permission.*
import java.util.logging.Logger


class PermissionActivity : AppCompatActivity() {

    private val PICK_PHOTO = 5
    val log = Logger.getLogger(PermissionActivity::class.java.name)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permission)
        checkPermission(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.READ_CONTACTS
            )
        )

        if ((ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) and (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
                    == PackageManager.PERMISSION_GRANTED)
        ) { this.showLocalisation() }
        ImagePIcker.setOnClickListener { this.pickImage() }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
            == PackageManager.PERMISSION_GRANTED
        ) {
            this.showContact()
        }

    }

    @SuppressLint("MissingPermission")
    private fun showLocalisation() {
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val locationListener = object : LocationListener {

            override fun onLocationChanged(location: Location) {
                // Called when a new location is found by the network location provider.
                latitudeEdit.text = location.latitude.toString()
                longitudeEdit.text = location.longitude.toString()
            }

            override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {
            }

            override fun onProviderEnabled(provider: String) {
            }

            override fun onProviderDisabled(provider: String) {
            }
        }

        locationManager.requestLocationUpdates(locationManager.getBestProvider(Criteria(),true), 0, 0f, locationListener)
    }

    private fun showContact() {
        val cursor =
            contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null)
        val nom = arrayListOf<String>()
        val num = arrayListOf<String>()
        if(cursor != null && cursor.moveToFirst())
        {
            do {
                nom.add(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)))
                num.add(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)))
            }while (cursor.moveToNext())
        }
        val adapter =
            ContactListViewAdapter(this, nom.toTypedArray(), num.toTypedArray())
        listViewContact.adapter = adapter
    }

    private fun checkPermission(perms: Array<String>) {
        val toAsk: ArrayList<String> = arrayListOf()
        for (perm in perms) {
            if (ContextCompat.checkSelfPermission(this, perm)
                != PackageManager.PERMISSION_GRANTED
            ) {
                toAsk.add(perm)
            }
        }
        val toAskArray: Array<String> = toAsk.toTypedArray()

        if (toAskArray.isNotEmpty()) {
            ActivityCompat.requestPermissions(
                this,
                toAskArray, 1
            )
        }

    }

    private fun pickImage() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(Intent.createChooser(intent, "Choisir une image"), PICK_PHOTO)
    }

    public override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
            == PackageManager.PERMISSION_GRANTED
        ) {
            this.showContact()
        }
        if ((ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) and (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
                    == PackageManager.PERMISSION_GRANTED)
        ) { this.showLocalisation() }

    }
    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_PHOTO && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                return
            } else {
                val uri = data.data
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, uri)
                ImagePIcker.setImageBitmap(bitmap)
            }
        }
    }

}