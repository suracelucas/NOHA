package ar.unlam.nohaapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView

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
        var convertView = convertView
        val expandedListText = getChild(listPosition, expandedListPosition) as String

        if (convertView == null) {
            val layoutInflater =
                this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.eventos_list, null)
        }

        val expandedListTextView = convertView!!.findViewById<TextView>(R.id.eventos_tv)
        expandedListTextView.text = expandedListText

        return convertView
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
        var convertView = convertView
        val listTitle = getGroup(listPosition) as String
        if (convertView == null) {
            val layoutInflater =
                this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.lugar_list, null)
        }
        val listTitleTextView = convertView!!.findViewById<TextView>(R.id.lugar_tv)
        listTitleTextView.text = listTitle
        return convertView
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun isChildSelectable(listPosition: Int, expandedListPosition: Int): Boolean {
        return true
    }

}