package fr.isen.billaud.androidtoolbox

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_life_cycle.*
import java.util.logging.Logger

class LifeCycleActivity : AppCompatActivity(), LifeCycleFragment.OnFragmentInteractionListener {
    var color = 200
    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    val Log = Logger.getLogger(LifeCycleActivity::class.java.name)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_life_cycle)
        fragmentSwitcher.setOnClickListener {
            lifeCycleFragment.fragmentManager?.beginTransaction()
                ?.replace(lifeCycleFragment.id, LifeCycleFragment.newInstance(color))?.commit()
            color = 255 - color
        }
    }


    override fun onPause() {
        super.onPause()
        Log.info("EN PAUSE")
        lfTextView.text = "EN PAUSE"
    }

    override fun onStart() {
        lfTextView.text = "START"
        Log.info("START")
        super.onStart()

    }

    override fun onResume() {
        lfTextView.text = "RESUME"
        Log.info("RESUME")
        super.onResume()
    }

    override fun onRestart() {
        lfTextView.text = "RESTART"
        Log.info("RESTART")
        super.onRestart()
    }

    override fun onStop() {
        lfTextView.text = "STOP"
        Log.info("STOP")
        super.onStop()
    }

    override fun onDestroy() {
        Toast.makeText(this, "Destruction", Toast.LENGTH_LONG).show()
        Log.info("DESTROY")
        super.onDestroy()
    }


}
