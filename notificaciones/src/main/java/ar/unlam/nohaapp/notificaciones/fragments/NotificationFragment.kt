package ar.unlam.nohaapp.notificaciones.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListAdapter
import android.widget.ExpandableListView
import androidx.fragment.app.Fragment
import ar.unlam.nohaapp.notificaciones.Datasource
import ar.unlam.nohaapp.notificaciones.adapters.NotificacionesListAdapter
import ar.unlam.nohaapp.notificaciones.databinding.FragmentNotificationBinding


class NotificationFragment : Fragment() {
    lateinit var notificacionesBinding: FragmentNotificationBinding
    private lateinit var expandableListView: ExpandableListView
    private lateinit var adapter: ExpandableListAdapter
    private lateinit var lugarList: List<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        notificacionesBinding = FragmentNotificationBinding.inflate(LayoutInflater.from(context))
        super.onCreate(savedInstanceState)
        expandableListView = notificacionesBinding.eListView

        val listData = Datasource().loadLugares()
        lugarList = ArrayList(listData.keys)
        adapter = NotificacionesListAdapter(this, lugarList as ArrayList<String>, listData)
        expandableListView.setAdapter(adapter)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return notificacionesBinding.root
    }
}