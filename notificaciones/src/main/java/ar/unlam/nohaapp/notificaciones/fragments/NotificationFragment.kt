package ar.unlam.nohaapp.notificaciones.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListAdapter
import android.widget.ExpandableListView
import androidx.fragment.app.Fragment
import androidx.room.Room
import androidx.room.RoomDatabase
import ar.unlam.nohaapp.notificaciones.adapters.NotificacionesListAdapter
import ar.unlam.nohaapp.notificaciones.data.ActividadEntity
import ar.unlam.nohaapp.notificaciones.data.LugarEntity
import ar.unlam.nohaapp.notificaciones.data.Migration1to2
import ar.unlam.nohaapp.notificaciones.data.RoomNohaDB
import ar.unlam.nohaapp.notificaciones.databinding.FragmentNotificationBinding


class NotificationFragment : Fragment() {
    private lateinit var notificacionesBinding: FragmentNotificationBinding
    private lateinit var expandableListView: ExpandableListView
    private lateinit var adapter: ExpandableListAdapter
    private lateinit var lugarList: List<LugarEntity>
    private lateinit var actividadList: List<ActividadEntity>
    private lateinit var actividadesList: HashMap<LugarEntity, List<ActividadEntity>>
    private lateinit var database: RoomNohaDB

    override fun onCreate(savedInstanceState: Bundle?) {
        notificacionesBinding = FragmentNotificationBinding.inflate(LayoutInflater.from(context))
        super.onCreate(savedInstanceState)
        expandableListView = notificacionesBinding.eListView
        getInstanceDatabase()
        getActividadPorLugar()

        adapter =
            NotificacionesListAdapter(this, lugarList, actividadesList)
        expandableListView.setAdapter(adapter)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return notificacionesBinding.root
    }

    private fun getInstanceDatabase() {
        database = Room.databaseBuilder(
            requireContext(),
            RoomNohaDB::class.java,
            "noha_db"
        )
            .addMigrations(Migration1to2())
            .allowMainThreadQueries()
            .build()
    }

    private fun getActividadPorLugar() {
        actividadList = database.actividadDao().getAll()
        lugarList = database.lugarDao().getAll()
        actividadesList = hashMapOf()

        val buffetList: MutableList<ActividadEntity> = mutableListOf()
        val salonList: MutableList<ActividadEntity> = mutableListOf()
        val gymList: MutableList<ActividadEntity> = mutableListOf()
        val natatorioList: MutableList<ActividadEntity> = mutableListOf()
        val spaList: MutableList<ActividadEntity> = mutableListOf()

        for (act in actividadList) {
            when (act.lugar.nombreLugar) {
                "Buffet" -> buffetList.add(act)
                "Salón" -> salonList.add(act)
                "Gimnasio" -> gymList.add(act)
                "Natatorio" -> natatorioList.add(act)
                "Spa" -> spaList.add(act)
            }
        }

        for (lug in lugarList) {
            when (lug.nombreLugar) {
                "Buffet" -> actividadesList[lug] = buffetList
                "Salón" -> actividadesList[lug] = salonList
                "Gimnasio" -> actividadesList[lug] = gymList
                "Natatorio" -> actividadesList[lug] = natatorioList
                "Spa" -> actividadesList[lug] = spaList
            }
        }

    }
}