package fr.isen.billaud.androidtoolbox.adapter

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import fr.isen.billaud.androidtoolbox.CircleTransformation
import fr.isen.billaud.androidtoolbox.Model.Results
import fr.isen.billaud.androidtoolbox.R
import kotlinx.android.synthetic.main.random_user.view.*

class RandomUerPLAdpater : PagedListAdapter<Results, RandomUerPLAdpater.RandomUserHolder>(getResultDiff()) {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RandomUserHolder {
        val inflater = LayoutInflater.from(p0.context)
        return RandomUserHolder(inflater.inflate(R.layout.random_user, null))
    }

    override fun onBindViewHolder(p0: RandomUserHolder, p1: Int) {
        val peon = getItem(p1) ?:return
        p0.tvNom?.text = peon.name.name
        p0.tvMail?.text = peon.email
        p0.tvAdress?.text = peon.location.adress
        Picasso.get().load(peon.picture.medium).resize(300, 300).centerCrop()
            .transform(CircleTransformation()).into(p0.ivAvatar)
    }


    class RandomUserHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNom = itemView.WebServiceNom
        val tvMail = itemView.WebServiceMail
        val tvAdress = itemView.WebServiceAdress
        val ivAvatar = itemView.WebServiceAvatar
    }

    companion object {

        fun getResultDiff() = object : DiffUtil.ItemCallback<Results>() {
            override fun areItemsTheSame(p0: Results, p1: Results): Boolean {
                return p0.login.uuid == p1.login.uuid
            }

            override fun areContentsTheSame(p0: Results, p1: Results): Boolean {
                return p0 == p1
            }
        }
    }
}