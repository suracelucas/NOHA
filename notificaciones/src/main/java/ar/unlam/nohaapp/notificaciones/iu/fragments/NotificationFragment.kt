package ar.unlam.nohaapp.notificaciones.iu.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListAdapter
import android.widget.ExpandableListView
import androidx.fragment.app.Fragment
import ar.unlam.nohaapp.notificaciones.data.local.RoomNohaDB
import ar.unlam.nohaapp.notificaciones.data.model.DatabaseProvider
import ar.unlam.nohaapp.notificaciones.data.model.LugaresConActividadesEntity
import ar.unlam.nohaapp.notificaciones.databinding.FragmentNotificationBinding
import ar.unlam.nohaapp.notificaciones.iu.adapters.NotificacionesListAdapter
import ar.unlam.nohaapp.notificaciones.iu.viewmodel.NotificationFragmentViewModel

class NotificationFragment : Fragment() {
    private lateinit var notificacionesBinding: FragmentNotificationBinding
    private lateinit var expandableListView: ExpandableListView
    private lateinit var adapter: ExpandableListAdapter
    private lateinit var actividadEnLugarList: List<LugaresConActividadesEntity>
    private lateinit var database: RoomNohaDB

    override fun onCreate(savedInstanceState: Bundle?) {
        val notificationFragmentViewModel : NotificationFragmentViewModel
        notificacionesBinding = FragmentNotificationBinding.inflate(LayoutInflater.from(context))
        val provider = DatabaseProvider()
        database = provider.getInstanceDatabase(requireContext())
        super.onCreate(savedInstanceState)

        actividadEnLugarList = database.actividadEnLugarDao().getAll()
        expandableListView = notificacionesBinding.eListView
        adapter =
            NotificacionesListAdapter(this, actividadEnLugarList, database)
        expandableListView.setAdapter(adapter)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return notificacionesBinding.root
    }

}