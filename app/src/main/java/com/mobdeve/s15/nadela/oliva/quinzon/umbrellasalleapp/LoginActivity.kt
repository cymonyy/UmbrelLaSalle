package com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp.databinding.AdminStudentLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var viewBinding : AdminStudentLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.viewBinding = AdminStudentLoginBinding.inflate(layoutInflater)
        setContentView(this.viewBinding.root)


        this.viewBinding.etLoginNum.hint = intent.getStringExtra("user") + " Number"
    }

}