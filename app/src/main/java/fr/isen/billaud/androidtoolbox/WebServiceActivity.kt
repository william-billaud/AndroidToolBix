package fr.isen.billaud.androidtoolbox

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.billaud.androidtoolbox.Adapter.RandomUserAdapter
import fr.isen.billaud.androidtoolbox.Model.RandomUser
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_web_service.*
import java.util.logging.Logger


class WebServiceActivity : AppCompatActivity() {
    val log = Logger.getLogger(WebServiceActivity::class.java.name)
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_service)
        // Instantiate the RequestQueue.


        RecyclerRandomUser.layoutManager=LinearLayoutManager(this)
        val queue = Volley.newRequestQueue(this)
        val url = "https://randomuser.me/api?results=30&gender=female"

        // Request a string response from the provided URL.
        val stringRequest = StringRequest(Request.Method.GET, url,
            Response.Listener<String> { response ->
                // Display the first 500 characters of the response string.
                log.info(response)
                val user = Gson().fromJson(response,RandomUser::class.java)
                if(user !=null)
                {
                    RecyclerRandomUser.adapter=
                        RandomUserAdapter(ArrayList(user.results), this)
                }
            },
            Response.ErrorListener { textView.text = "That didn't work!" })

// Add the request to the RequestQueue.
        queue.add(stringRequest)

    }
}
