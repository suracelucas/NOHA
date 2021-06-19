package ar.unlam.nohaapp

import android.os.Bundle
import android.widget.ExpandableListAdapter
import android.widget.ExpandableListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ar.unlam.nohaapp.NotificacionesData.data

class NotificacionesActivity : AppCompatActivity() {
    private var expandableListView: ExpandableListView? = null
    private var adapter: ExpandableListAdapter? = null
    private var lugarList: List<String>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notificaciones)
        expandableListView = findViewById(R.id.eListView)

        if (expandableListView != null) {
            val listData = data
            lugarList = ArrayList(listData.keys)
            adapter = NotificacionesListAdapter(this, lugarList as ArrayList<String>, listData)
            expandableListView!!.setAdapter(adapter)

            expandableListView!!.setOnChildClickListener { _, _, groupPosition, childPosition, _ ->
                Toast.makeText(
                    applicationContext,
                    "Clickeado: " + (lugarList as ArrayList<String>)[groupPosition] + " -> " + listData[(
                            lugarList as
                                    ArrayList<String>
                            )
                            [groupPosition]]!!.get(
                        childPosition
                    ),
                    Toast.LENGTH_SHORT
                ).show()
                false
            }
        }
    }
}