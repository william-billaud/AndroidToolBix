package fr.isen.billaud.androidtoolbox

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import java.util.logging.Logger

class LoginActivity : AppCompatActivity() {

    val log = Logger.getLogger(LoginActivity::class.java.name)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val sharedPreference = getSharedPreferences("PREFERENCE_ANDROIDTOOLBOX", Context.MODE_PRIVATE)
        val editor = sharedPreference.edit()
        validationLogin.setOnClickListener {
            // make a toast on button click event
            if (this.verifieLog(emailLoginField?.text?.toString(), passLoginField?.text?.toString())) {
                Toast.makeText(this, "mot de passe OK", Toast.LENGTH_LONG).show()
                editor.putString("id", emailLoginField?.text?.toString())
                editor.putString("pass", passLoginField?.text?.toString())
                editor.apply()
                log.info("log OK")
                this.goMain()
            } else {
                Toast.makeText(this, "l'authentification à échoué", Toast.LENGTH_LONG).show()
                log.info("erreur log")
            }

        }
        if (this.verifieLog(sharedPreference.getString("id", ""), sharedPreference.getString("pass", ""))) {
            log.info("Connexion automatique")
            this.goMain()
        }
    }

    private fun verifieLog(id: String?, pass: String?): Boolean {
        log.info("Tentative de log")
        return id.equals("admin") && pass.equals("123")
    }

    fun goMain() {
        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
    }
}
