package fr.isen.billaud.androidtoolbox

import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_life_cycle.*
import java.util.logging.Logger


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [LifeCycleFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [LifeCycleFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class LifeCycleFragment : Fragment() {
    val Log = Logger.getLogger(LifeCycleFragment::class.java.name)
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentTv?.text = "ON CREATE VIEW:"
        Log.info("ON CREATE VIEW")
        val color = arguments?.getInt("color")
        Log.info("color : $color")
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_life_cycle, container, false)
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
        fragmentTv?.text = "ON ATTACH"
        Log.info("ON ATTACH")
    }

    override fun onDetach() {
        super.onDetach()
        fragmentTv?.text = "ON DETACH"
        Log.info("ON DETACH")
        listener = null
    }

    override fun onPause() {
        super.onPause()
        fragmentTv?.text = "ON Pause"
        Log.info("ON pause")
    }

    override fun onResume() {
        fragmentTv?.text = "ON Resume"
        Log.info("ON Resume")
        super.onResume()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        fragmentTv?.text = "ON Activity Created"
        Log.info("ON Activity Created")
        super.onActivityCreated(savedInstanceState)
    }

    override fun onDestroyView() {
        fragmentTv?.text = "ON DestroyView"
        Log.info("ON Destroy view")
        super.onDestroyView()
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LifeCycleFragment.
         */
        fun newInstance(color: Int): LifeCycleFragment {
            val args = Bundle()
            args.putInt("color", color)
            val fragment = LifeCycleFragment()
            fragment.arguments = args
            return fragment

        }
    }


}
