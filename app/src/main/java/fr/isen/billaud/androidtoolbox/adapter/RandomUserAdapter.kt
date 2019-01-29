package fr.isen.billaud.androidtoolbox.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import fr.isen.billaud.androidtoolbox.CircleTransformation
import fr.isen.billaud.androidtoolbox.Model.Results
import fr.isen.billaud.androidtoolbox.R
import kotlinx.android.synthetic.main.random_user.view.*

class RandomUserAdapter(val items : ArrayList<Results>,val context : Context):
    RecyclerView.Adapter<ViewHolder>() {


    override fun onCreateViewHolder(p0: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(this.context).inflate(
                R.layout.random_user,
                p0,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(p0: ViewHolder, position: Int) {
        p0.tvNom?.text = items[position].name.name
        p0.tvMail?.text= items[position].email
        p0.tvAdress?.text = items[position].location.adress
        Picasso.get().load(items[position].picture.medium).resize(300,300).centerCrop().transform(CircleTransformation()).into( p0.ivAvatar)
    }

}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val tvNom = view.WebServiceNom
    val tvMail = view.WebServiceMail
    val tvAdress = view.WebServiceAdress
    val ivAvatar = view.WebServiceAvatar
}