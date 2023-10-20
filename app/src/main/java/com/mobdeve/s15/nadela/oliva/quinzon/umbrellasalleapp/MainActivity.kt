package com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forgot_password)

        val forgotPasswordProceedBtn = findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.btn_forgotPasswordProceed)
        forgotPasswordProceedBtn.setOnClickListener{
//            val Intent = Intent(this.EmailVerificationSentActivity::class.java)
        }

    }
    /**
     *
     *
     * Changes from irah-dev-red-section branch
     *
     *
     * */



}