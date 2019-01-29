package fr.isen.billaud.androidtoolbox

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var mAdView : AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lifeCycleIcon.setOnClickListener {
            startActivity(Intent(this@MainActivity, LifeCycleActivity::class.java))
        }
        disconnectHome.setOnClickListener {
            val sharedPreference = getSharedPreferences("PREFERENCE_ANDROIDTOOLBOX", Context.MODE_PRIVATE)
            val editor = sharedPreference.edit()
            editor.putString("id", "")
            editor.putString("pass", "")
            editor.apply()
            startActivity(
                Intent(this@MainActivity, LoginActivity::class.java).addFlags(
                    Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                )
            )
            finish()
        }
        SauvegardeIcon.setOnClickListener {
            startActivity(Intent(this@MainActivity, RegisterActivity::class.java))
        }

        PermissionIcon.setOnClickListener {
            startActivity(Intent(this@MainActivity, PermissionActivity::class.java))
        }
        webServiceIcon.setOnClickListener {
            startActivity(Intent(this@MainActivity, WebServiceActivity::class.java))
        }


        MobileAds.initialize(this,
            "ca-app-pub-2370569704081351~4488862396")

        mAdView=adViewMain
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

    }
}
