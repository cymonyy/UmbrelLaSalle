package com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        this.viewBinding.cvStudent.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@MainActivity, StartScreenActivity::class.java)
            intent.putExtra("user", "STUDENT")
            startActivity(intent)
        })

        this.viewBinding.cvAdmin.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@MainActivity, StartScreenActivity::class.java)
            intent.putExtra("user", "ADMIN")
            startActivity(intent)
        })
    }
}