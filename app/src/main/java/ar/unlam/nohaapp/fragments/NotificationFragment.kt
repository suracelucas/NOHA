package ar.unlam.nohaapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ar.unlam.nohaapp.databinding.FragmentNotificationBinding

lateinit var notificacionesBinding: FragmentNotificationBinding
class NotificationFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        notificacionesBinding = FragmentNotificationBinding.inflate(LayoutInflater.from(context))
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return notificacionesBinding.root
    }
}