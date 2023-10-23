package com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.DisplayMetrics
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.card.MaterialCardView
import com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp.databinding.AdminStudentLoginBinding
import com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp.databinding.StudentCreateAccountBinding
import java.util.regex.Matcher
import java.util.regex.Pattern

class StudentCreateAccountActivity : AppCompatActivity() {

    private lateinit var viewBinding: StudentCreateAccountBinding
    private lateinit var etFirstName: EditText
    private lateinit var etLastName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etMobile: EditText
    private lateinit var etPassword: EditText
    private lateinit var etConfirmPass: EditText

    private lateinit var pattern: Pattern
    private lateinit var matcher: Matcher
    private var errorOccured = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.viewBinding = StudentCreateAccountBinding.inflate(layoutInflater)
        setContentView(this.viewBinding.root)

        etFirstName = this.viewBinding.etFirstname
        etLastName = this.viewBinding.etLastname
        etEmail = this.viewBinding.etEmail
        etMobile = this.viewBinding.etPhonenum
        etPassword = this.viewBinding.etPassword
        etConfirmPass = this.viewBinding.etConfPassword


        this.viewBinding.btProceed.setOnClickListener(View.OnClickListener {
            val firstName = etFirstName.text
            val lastName = etLastName.text.toString()
            val email = etEmail.text.toString()
            val mobileNumber = etMobile.text.toString()
            val password = etPassword.text.toString()
            val confirmPassword = etConfirmPass.text.toString()


            //First Name Errors
            if(TextUtils.isEmpty(firstName)){
                viewBinding.tvFirstNameError.visibility = View.VISIBLE
                viewBinding.tvFirstNameError.text = resources.getStringArray(R.array.register_name)[0]
                val parentCard : MaterialCardView =  etFirstName.parent as MaterialCardView
                parentCard.strokeColor = Color.parseColor("#E31414")
                parentCard.strokeWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1.5f, resources.displayMetrics ).toInt()
                errorOccured = true
            }

            //Last Name Errors
            if(TextUtils.isEmpty(lastName)){
                viewBinding.tvLastNameError.visibility = View.VISIBLE
                viewBinding.tvLastNameError.text = resources.getStringArray(R.array.register_name)[1]
                val parentCard : MaterialCardView =  etLastName.parent as MaterialCardView
                parentCard.strokeColor = Color.parseColor("#E31414")
                parentCard.strokeWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1.5f, resources.displayMetrics ).toInt()
                errorOccured = true
            }

            //Email Errors
            pattern = Pattern.compile("[a-z](?:[a-z]+(_|\\.))+[a-z]+@dlsu.edu.ph") //DLSU email

            matcher = pattern.matcher(email)
            if(TextUtils.isEmpty(email)){
                viewBinding.tvEmailError.visibility = View.VISIBLE
                viewBinding.tvEmailError.text = resources.getStringArray(R.array.register_email)[0]
                val parentCard : MaterialCardView =  etEmail.parent as MaterialCardView
                parentCard.strokeColor = Color.parseColor("#E31414")
                parentCard.strokeWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1.5f, resources.displayMetrics ).toInt()
                errorOccured = true
            }
            else if(!matcher.matches()){
                viewBinding.tvEmailError.visibility = View.VISIBLE
                viewBinding.tvEmailError.text = resources.getStringArray(R.array.register_email)[1]
                val parentCard : MaterialCardView =  etEmail.parent as MaterialCardView
                parentCard.strokeColor = Color.parseColor("#E31414")
                parentCard.strokeWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1.5f, resources.displayMetrics ).toInt()
                errorOccured = true
            }

            //Mobile Number Errors
            Log.d("Mobile Number", mobileNumber)
            pattern = Pattern.compile("09[0-9]{9}") //Philippines number
            matcher = pattern.matcher(mobileNumber)
            if(TextUtils.isEmpty(mobileNumber)){
                viewBinding.tvPhoneError.visibility = View.VISIBLE
                viewBinding.tvPhoneError.text = resources.getStringArray(R.array.register_mobile)[0]
                val parentCard : MaterialCardView =  etMobile.parent as MaterialCardView
                parentCard.strokeColor = Color.parseColor("#E31414")
                parentCard.strokeWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1.5f, resources.displayMetrics ).toInt()
                errorOccured = true
            }
            else if(!matcher.find() || mobileNumber.length != 11){
                Log.d("matched", matcher.find().toString())
                viewBinding.tvPhoneError.visibility = View.VISIBLE
                viewBinding.tvPhoneError.text = resources.getStringArray(R.array.register_mobile)[1]
                val parentCard : MaterialCardView =  etMobile.parent as MaterialCardView
                parentCard.strokeColor = Color.parseColor("#E31414")
                parentCard.strokeWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1.5f, resources.displayMetrics ).toInt()
                errorOccured = true
            }

            //Password Errors
            if(TextUtils.isEmpty(password)){
                viewBinding.tvPassError.visibility = View.VISIBLE
                viewBinding.tvPassError.text = resources.getStringArray(R.array.register_password)[0]
                val parentCard : MaterialCardView =  etPassword.parent as MaterialCardView
                parentCard.strokeColor = Color.parseColor("#E31414")
                parentCard.strokeWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1.5f, resources.displayMetrics ).toInt()
                errorOccured = true
            }

            //Confirm Password Errors
            if(TextUtils.isEmpty(confirmPassword)){
                viewBinding.tvConfirmError.visibility = View.VISIBLE
                viewBinding.tvConfirmError.text = resources.getStringArray(R.array.register_confirm_password)[0]
                val parentCard : MaterialCardView =  etConfirmPass.parent as MaterialCardView
                parentCard.strokeColor = Color.parseColor("#E31414")
                parentCard.strokeWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1.5f, resources.displayMetrics ).toInt()
                errorOccured = true
            }
        })

        etFirstName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            @SuppressLint("ResourceAsColor", "PrivateResource")
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewBinding.tvFirstNameError.visibility = View.GONE
                val parentCard : MaterialCardView =  etFirstName.parent as MaterialCardView
                parentCard.strokeWidth = 0
                errorOccured = false
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        etLastName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            @SuppressLint("ResourceAsColor", "PrivateResource")
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewBinding.tvLastNameError.visibility = View.GONE
                val parentCard : MaterialCardView =  etLastName.parent as MaterialCardView
                parentCard.strokeWidth = 0
                errorOccured = false
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        etEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            @SuppressLint("ResourceAsColor", "PrivateResource")
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewBinding.tvEmailError.visibility = View.GONE
                val parentCard : MaterialCardView =  etEmail.parent as MaterialCardView
                parentCard.strokeWidth = 0
                errorOccured = false
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        etMobile.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            @SuppressLint("ResourceAsColor", "PrivateResource")
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewBinding.tvPhoneError.visibility = View.GONE
                val parentCard : MaterialCardView =  etMobile.parent as MaterialCardView
                parentCard.strokeWidth = 0
                errorOccured = false
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        etPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            @SuppressLint("ResourceAsColor", "PrivateResource")
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewBinding.tvPassError.visibility = View.GONE
                val parentCard : MaterialCardView =  etPassword.parent as MaterialCardView
                parentCard.strokeWidth = 0
                errorOccured = false
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        etConfirmPass.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            @SuppressLint("ResourceAsColor", "PrivateResource")
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewBinding.tvConfirmError.visibility = View.GONE
                val parentCard : MaterialCardView =  etConfirmPass.parent as MaterialCardView
                parentCard.strokeWidth = 0
                errorOccured = false
            }
            override fun afterTextChanged(s: Editable?) {}
        })

    }

}