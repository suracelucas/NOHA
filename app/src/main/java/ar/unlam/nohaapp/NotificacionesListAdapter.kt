package ar.unlam.nohaapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.switchmaterial.SwitchMaterial

class NotificacionesListAdapter internal constructor(
    private val context: NotificacionesActivity,
    private val lugarList: List<String>,
    private val eventosList: HashMap<String, List<String>>
) : BaseExpandableListAdapter() {

    override fun getChild(listPosition: Int, expandedListPosition: Int): Any {
        return this.eventosList[this.lugarList[listPosition]]!![expandedListPosition]
    }

    override fun getChildId(listPosition: Int, expandedListPosition: Int): Long {
        return expandedListPosition.toLong()
    }

    override fun getChildView(
        listPosition: Int,
        expandedListPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup
    ): View {
        var convertedView = convertView
        val expandedListText = getChild(listPosition, expandedListPosition) as String

        if (convertedView == null) {
            val layoutInflater =
                this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertedView = layoutInflater.inflate(R.layout.eventos_list, parent, false)
        }

        val expandedListTextView = convertedView!!.findViewById<TextView>(R.id.eventos_tv)
        expandedListTextView.text = expandedListText

        val switch: SwitchMaterial = convertedView.findViewById(R.id.switch_noti)
        switch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Toast.makeText(
                    context,
                    "Notificar $expandedListText: SI",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    context,
                    "Notificar $expandedListText: NO",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        return convertedView
    }

    override fun getChildrenCount(listPosition: Int): Int {
        return this.eventosList[this.lugarList[listPosition]]!!.size
    }

    override fun getGroup(listPosition: Int): Any {
        return this.lugarList[listPosition]
    }

    override fun getGroupCount(): Int {
        return this.lugarList.size
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
                this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertedView = layoutInflater.inflate(R.layout.lugar_list, parent, false)
        }
        val listTitleTextView = convertedView!!.findViewById<TextView>(R.id.lugar_tv)
        listTitleTextView.text = listTitle
        return convertedView
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun isChildSelectable(listPosition: Int, expandedListPosition: Int): Boolean {
        return true
    }

}