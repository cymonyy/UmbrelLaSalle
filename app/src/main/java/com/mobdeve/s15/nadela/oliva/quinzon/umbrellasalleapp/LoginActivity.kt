package com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp.databinding.AdminStudentLoginBinding
import java.util.regex.Matcher
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity() {

    private lateinit var viewBinding : AdminStudentLoginBinding

    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText

    private lateinit var pattern: Pattern
    private lateinit var matcher: Matcher
    private var errorOccured = false

    private var db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private var userRef: CollectionReference = db.collection("Users")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.viewBinding = AdminStudentLoginBinding.inflate(layoutInflater)
        setContentView(this.viewBinding.root)

        etEmail = this.viewBinding.etLoginEmail
        etPassword = this.viewBinding.etLoginPassword

        this.viewBinding.btProceed.setOnClickListener(View.OnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

        })
    }

}