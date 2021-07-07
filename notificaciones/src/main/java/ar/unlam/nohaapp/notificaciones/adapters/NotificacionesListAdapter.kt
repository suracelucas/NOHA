package ar.unlam.nohaapp.notificaciones.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import android.widget.Toast
import ar.unlam.nohaapp.notificaciones.R
import ar.unlam.nohaapp.notificaciones.data.*
import ar.unlam.nohaapp.notificaciones.fragments.NotificationFragment
import com.google.android.material.switchmaterial.SwitchMaterial

class NotificacionesListAdapter internal constructor(
    private val context: NotificationFragment,
    private val actividadesList: List<LugaresConActividadesEntity>,
    private val database: RoomNohaDB
) : BaseExpandableListAdapter() {

    override fun getChild(listPosition: Int, childPosition: Int): Any {
        return actividadesList[listPosition].actividades[childPosition]
    }

    override fun getChildId(listPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun getChildView(
        listPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup,
    ): View {
        var convertedView = convertView

        val currentChild = actividadesList[listPosition].actividades[childPosition]
        val nombreActividad = currentChild.nombreActividad

        val layoutInflater =
            context.requireActivity()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        convertedView = layoutInflater.inflate(R.layout.eventos_list, parent, false)

        val switch: SwitchMaterial = convertedView!!.findViewById(R.id.switch_noti)
        switch.isChecked = getNotificarState(nombreActividad)

        switch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                setNotificarOn(nombreActividad)
                Toast.makeText(
                    context.activity,
                    "Notificar $nombreActividad: SI",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                setNotificarOff(nombreActividad)
                Toast.makeText(
                    context.activity,
                    "Notificar $nombreActividad: NO",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }


        val childTextView = convertedView.findViewById<TextView>(R.id.eventos_tv)
        childTextView.text = nombreActividad

        return convertedView
    }

    override fun getChildrenCount(listPosition: Int): Int {
        return actividadesList[listPosition].actividades.size
    }

    override fun getGroup(listPosition: Int): Any {
        return actividadesList[listPosition]
    }

    override fun getGroupCount(): Int {
        return actividadesList.size
    }

    override fun getGroupId(listPosition: Int): Long {
        return listPosition.toLong()
    }

    override fun getGroupView(
        listPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup
    ): View {
        var convertedView = convertView
        val currentGroup = actividadesList[listPosition]
        val listTitle = currentGroup.lugar.nombreLugar

        val layoutInflater =
            context.requireActivity()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        convertedView = layoutInflater.inflate(R.layout.lugar_list, parent, false)

        val listTitleTextView = convertedView!!.findViewById<TextView>(R.id.lugar_tv)
        listTitleTextView.text = listTitle

        return convertedView
    }

    override fun hasStableIds(): Boolean {
        return true
    }

    override fun isChildSelectable(listPosition: Int, childPosition: Int): Boolean {
        return true
    }

    private fun getNotificarState(nombreActividad: String): Boolean {
        return database.actividadDao().getNotificarByName(nombreActividad)
    }

    private fun setNotificarOn(nombreActividad: String) {
        database.actividadDao().setNotificarByID(nombreActividad, 1)
    }

    private fun setNotificarOff(nombreActividad: String) {
        database.actividadDao().setNotificarByID(nombreActividad, 0)
    }

}