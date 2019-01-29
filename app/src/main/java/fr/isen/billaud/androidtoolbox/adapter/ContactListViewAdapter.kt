package fr.isen.billaud.androidtoolbox.adapter

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import fr.isen.billaud.androidtoolbox.R

class ContactListViewAdapter(private val context: Activity,private val nom : Array<String>,private val num : Array<String>) : ArrayAdapter<String>(context,
    R.layout.contact_adatpter,nom) {
    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.contact_adatpter, null, true)

        val nomContact = rowView.findViewById(R.id.nomContact) as TextView
        val numeroContact = rowView.findViewById(R.id.numeroContact) as TextView

        nomContact.text = nom[position]
        numeroContact.text = num[position]

        return rowView
    }
}