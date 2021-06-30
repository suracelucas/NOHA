package ar.unlam.nohaapp.notificaciones.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import android.widget.Toast
import ar.unlam.nohaapp.notificaciones.Evento
import ar.unlam.nohaapp.notificaciones.R
import ar.unlam.nohaapp.notificaciones.fragments.NotificationFragment
import com.google.android.material.switchmaterial.SwitchMaterial

class NotificacionesListAdapter internal constructor(
    private val context: NotificationFragment,
    private val lugarList: List<String>,
    private val eventosList: HashMap<String, List<Evento>>
) : BaseExpandableListAdapter() {

    override fun getChild(listPosition: Int, childPosition: Int): Any {
        return eventosList[lugarList[listPosition]]!![childPosition]
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
        val nombreEvento = eventosList[lugarList[listPosition]]!![childPosition].nombreEvento
        var notificar = eventosList[lugarList[listPosition]]!![childPosition].notificar

        if (convertedView == null) {
            val layoutInflater =
                context.requireActivity()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertedView = layoutInflater.inflate(R.layout.eventos_list, parent, false)
        }

        val switch: SwitchMaterial = convertedView!!.findViewById(R.id.switch_noti)

        switch.isChecked = notificar
        switch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                notificar = true
                Toast.makeText(
                    context.activity,
                    "Notificar $nombreEvento: SI \n" /* +
                            " ParentID: ${getGroupId(listPosition)} \n ChildID: ${
                        getChildId(
                            listPosition,
                            childPosition
                        )
                    } \n Notificar: $notificar"
                    */,
                    Toast.LENGTH_SHORT
                ).show()

            } else {
                notificar = false
                Toast.makeText(
                    context.activity,
                    "Notificar $nombreEvento: NO \n" /* +
                            " ParentID: ${getGroupId(listPosition)} \n ChildID: ${
                        getChildId(
                            listPosition,
                            childPosition
                        )
                    } \n Notificar: $notificar"
                    */,
                    Toast.LENGTH_SHORT
                ).show()

            }
        }

        val childTextView = convertedView.findViewById<TextView>(R.id.eventos_tv)
        childTextView.text = nombreEvento

        return convertedView
    }

    override fun getChildrenCount(listPosition: Int): Int {
        return eventosList[lugarList[listPosition]]!!.size
    }

    override fun getGroup(listPosition: Int): Any {
        return lugarList[listPosition]
    }

    override fun getGroupCount(): Int {
        return lugarList.size
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
        val listTitle = getGroup(listPosition) as String

        if (convertedView == null) {
            val layoutInflater =
                context.requireActivity()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertedView = layoutInflater.inflate(R.layout.lugar_list, parent, false)
        }

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

}