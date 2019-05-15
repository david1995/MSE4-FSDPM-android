package at.eiwen.omdbexercise.Views.Activities

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import at.eiwen.omdbexercise.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        SetupNavigationDrawer(toolbar)
    }

    private fun SetupNavigationDrawer(toolbar: Toolbar)
    {
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        val configuration = AppBarConfiguration(navController.graph)
        toolbar.setupWithNavController(navController, configuration)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean
    {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
}
