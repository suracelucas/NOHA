package ar.unlam.nohaapp

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ar.unlam.nohaapp.databinding.ActivityMainBinding
import ar.unlam.nohaapp.fragments.HomeFragment
import ar.unlam.nohaapp.fragments.NotificationFragment

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding  = ActivityMainBinding.inflate(LayoutInflater.from(this))
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val homeFragment = HomeFragment()
        val notificationFragment = NotificationFragment()

        makeCurrentFragment(homeFragment)

        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.Home -> makeCurrentFragment(homeFragment)
                R.id.notificaciones->makeCurrentFragment(notificationFragment)
            }
            true
        }
    }

    private fun makeCurrentFragment(fragment: Fragment) =
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fl_wrapper, fragment)
                commit()
            }
}