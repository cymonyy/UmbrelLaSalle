package com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp.databinding.AdminCreateAccErrorBinding
import com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp.databinding.StartScreenBinding

class StartScreenActivity : AppCompatActivity() {

    private lateinit var viewBinding: StartScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.viewBinding = StartScreenBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)


        this.viewBinding.btProceed.setOnClickListener(View.OnClickListener {
            if (intent.getStringExtra("user").equals("ADMIN")){
                val newIntent = Intent(this@StartScreenActivity, AdminCreateAccountActivity::class.java)
                newIntent.putExtra("user", "ADMIN")
                startActivity(newIntent)
            }
            else {
                val newIntent = Intent(this@StartScreenActivity, StudentCreateAccountActivity::class.java)
                newIntent.putExtra("user", "STUDENT")
                startActivity(newIntent)
            }
        })

        this.viewBinding.btLogin.setOnClickListener(View.OnClickListener {
            if (intent.getStringExtra("user").equals("ADMIN")){
                val newIntent = Intent(this@StartScreenActivity, LoginActivity::class.java)
                newIntent.putExtra("user", "ADMIN")
                startActivity(newIntent)
            }
            else {
                val newIntent = Intent(this@StartScreenActivity, LoginActivity::class.java)
                newIntent.putExtra("user", "STUDENT")
                startActivity(newIntent)
            }
        })

        this.viewBinding.cvBackButton.root.setOnClickListener(View.OnClickListener {
            finish()
        })




    }


}