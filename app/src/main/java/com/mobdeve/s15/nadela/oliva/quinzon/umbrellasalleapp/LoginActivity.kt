package com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.card.MaterialCardView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
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

    private lateinit var authProfile: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.viewBinding = AdminStudentLoginBinding.inflate(layoutInflater)
        setContentView(this.viewBinding.root)

        etEmail = this.viewBinding.etLoginEmail
        etPassword = this.viewBinding.etLoginPassword

        authProfile = FirebaseAuth.getInstance()

        this.viewBinding.clBackButton.imageButton.setOnClickListener(View.OnClickListener {
            finish()
        })

        this.viewBinding.btProceed.setOnClickListener(View.OnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()


            //Email Errors
            pattern = Pattern.compile("[a-z](?:[a-z]+(_|\\.))+[a-z]+@dlsu.edu.ph") //DLSU email
            matcher = pattern.matcher(email)
            if(TextUtils.isEmpty(email)){
                triggerError(viewBinding.tvLoginEmailError, etEmail, resources.getStringArray(R.array.error_general_email_fields)[0])
            }
            else if(!matcher.matches()){
                triggerError(viewBinding.tvLoginPassError, etEmail, resources.getStringArray(R.array.error_general_email_fields)[1])
            }

            //Password Errors
            if(TextUtils.isEmpty(password)){
                triggerError(viewBinding.tvLoginPassError, etPassword, resources.getStringArray(R.array.error_general_pass_fields)[0])
            }

            if(!errorOccured){

                loginUser(email, password)
            }
        })


        etEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            @SuppressLint("ResourceAsColor", "PrivateResource")
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                cancelError(viewBinding.tvLoginEmailError, etEmail)
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        etPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            @SuppressLint("ResourceAsColor", "PrivateResource")
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                cancelError(viewBinding.tvLoginPassError, etPassword)
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun loginUser(email: String, password: String) {
        val customToken : String? = null

        customToken?.let {
            authProfile.signInWithCustomToken(it)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithCustomToken:success")
                        
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithCustomToken:failure", task.exception)
                        Toast.makeText(
                            baseContext,
                            "Authentication failed.",
                            Toast.LENGTH_SHORT,
                        ).show()
                        updateUI(null)
                    }
                }
        }

        /*authProfile.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if(task.isSuccessful){
                Toast.makeText(this@LoginActivity, "User Login Successful!", Toast.LENGTH_LONG).show()
            }
            else {
                try{
                    throw task.exception!!
                }
                catch (e: FirebaseAuthInvalidUserException){ //if does not exists
                    triggerError(viewBinding.tvLoginEmailError, etEmail, resources.getStringArray(R.array.error_login_email)[0])
                }
                catch (e: FirebaseAuthInvalidCredentialsException){ //if email and password mismatch
                    triggerError(viewBinding.tvLoginEmailError, etEmail,"")
                    etEmail.error = "This does not match!"

                    triggerError(viewBinding.tvLoginPassError, etPassword,"")
                    etEmail.error = "This does not match!"
                }


                catch (e: Exception){
                    Log.e(TAG, e.message.toString())
                    Toast.makeText(this@LoginActivity, "Error! " + e.message.toString(), Toast.LENGTH_LONG).show()
                }
            }

        }*/

    }

    private fun triggerError(textView: TextView, editText: EditText, message: String){
        textView.visibility = View.VISIBLE
        textView.text = message
        val parentCard : MaterialCardView =  editText.parent as MaterialCardView
        parentCard.strokeColor = Color.parseColor("#E31414")
        parentCard.strokeWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1.5f, resources.displayMetrics ).toInt()
        this.errorOccured = true
    }

    private fun cancelError(textView: TextView, editText: EditText) {
        textView.visibility = View.GONE
        val parentCard : MaterialCardView =  editText.parent as MaterialCardView
        parentCard.strokeWidth = 0
        this.errorOccured = false
    }

    companion object {
        const val TAG : String = "LoginActivity"
    }

}