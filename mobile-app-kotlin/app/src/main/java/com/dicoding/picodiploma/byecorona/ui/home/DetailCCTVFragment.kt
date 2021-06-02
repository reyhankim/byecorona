package com.dicoding.picodiploma.byecorona.ui.home

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.andrefrsousa.superbottomsheet.SuperBottomSheetFragment
import com.dicoding.picodiploma.byecorona.R
import com.dicoding.picodiploma.byecorona.data.model.Cluster
import com.dicoding.picodiploma.byecorona.databinding.FragmentDetailCctvBinding
import com.dicoding.picodiploma.byecorona.ui.cctv.ListCCTVActivity

class DetailCCTVFragment : SuperBottomSheetFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_detail_cctv, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            if (arguments != null) {
                view.findViewById<TextView>(R.id.textView4).text = arguments!!.getParcelable<Cluster>(
                    ID_CLUSTER)!!.clusterName
                view.findViewById<TextView>(R.id.textView3).text = arguments!!.getParcelable<Cluster>(
                    ID_CLUSTER)!!.cctvList!!.size.toString()
                view.findViewById<Button>(R.id.btn_cctv).setBackgroundColor(Color.parseColor("#007EFF"))
                view.findViewById<Button>(R.id.btn_cctv).setOnClickListener {
                    val intent = Intent(context, ListCCTVActivity::class.java)
                    intent.putExtra(ListCCTVActivity.DATA, arguments)
                    startActivity(intent)
                }
            }
        }
    }

    override fun getPeekHeight(): Int {
        return 600
    }

    companion object {
        const val ID_CLUSTER = "id_cluster"
    }
}