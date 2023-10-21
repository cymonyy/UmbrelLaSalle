package com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp.databinding.AdminCreateAccErrorBinding
import com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp.databinding.StartScreenBinding

class AdminCreateAccountActivity : AppCompatActivity(){

    private lateinit var viewBinding : AdminCreateAccErrorBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.viewBinding = AdminCreateAccErrorBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)


        this.viewBinding.tvProceedLogin.setOnClickListener(View.OnClickListener {
            val newIntent = Intent(this@AdminCreateAccountActivity, LoginActivity::class.java)
            newIntent.putExtra("user", "ADMIN")
            startActivity(newIntent)
            finish()
        })
    }

}