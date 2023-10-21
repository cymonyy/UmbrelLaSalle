package com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp.databinding.AdminStudentLoginBinding
import com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp.databinding.StudentCreateAccountBinding

class StudentCreateAccountActivity : AppCompatActivity() {

    private lateinit var viewBinding: StudentCreateAccountBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.viewBinding = StudentCreateAccountBinding.inflate(layoutInflater)
        setContentView(this.viewBinding.root)


    }
}