package fr.isen.billaud.androidtoolbox

import android.app.DatePickerDialog
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_register.*
import java.io.File
import java.io.FileInputStream
import java.text.SimpleDateFormat
import java.util.*
import java.util.logging.Logger


class RegisterActivity : AppCompatActivity() {
    private val log = Logger.getLogger(RegisterActivity::class.java.name)
    private val cal = Calendar.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        birthRegisterField.setOnClickListener {
            val dpd = DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                val formater = SimpleDateFormat("dd/MM/yyyy", Locale.FRENCH)
                birthRegisterField.setText(formater.format(cal.time))
            }
            val d = DatePickerDialog(
                this@RegisterActivity,
                dpd,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            )
            d.show()
        }

        validateRegister.setOnClickListener {
            if ((surnameRegisterField.text.toString() != "") && (nameRegisterField.text.toString() != "") && (birthRegisterField.text.toString() != "")) {
                birthRegisterField.text.toString()
                log.info("Date : " + birthRegisterField.text.toString())
                val user = User(
                    nameRegisterField.text.toString(), surnameRegisterField.text.toString(),
                    cal
                )
                log.info(user.toString())
                Toast.makeText(this, user.toString(), Toast.LENGTH_LONG).show()
                val file = this.getFile()
                file.writeText(Gson().toJson(user))
                log.info(Gson().toJson(user))

            } else {
                Toast.makeText(this, "Il faut remplir tous les champs", Toast.LENGTH_LONG).show()
            }
        }

        registerInfo.setOnClickListener {
            val file = this.getFile()
            val inputAsString = FileInputStream(file).bufferedReader().use { it.readText() }
            val user = Gson().fromJson(inputAsString,User::class.java)
            this.showDialog(user)
        }
    }
    private fun getFile() : File{
        val path = filesDir
        return File(path, "user.json")
    }

    private fun showDialog(user : User)
    {
        val builder = AlertDialog.Builder(this@RegisterActivity)
        builder.setTitle("info de "+user.name)
        builder.setMessage(" nom : "+user.name+"\n prénom : "+user.surname+"\n date de naissance : "+user.birthdateString()+"\n age : "+user.age())
        builder.create().show()
    }
}