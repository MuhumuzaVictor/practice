package com.viktadzy.practice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LogInActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        auth= Firebase.auth

         val signuptext= findViewById<TextView>(R.id.goTosignup)
        signuptext.setOnClickListener{
            val intent = Intent(this,SignUpActivity::class.java)
            startActivity(intent)
        }


        val loginbtn: Button= findViewById(R.id.loginbutton)
        loginbtn.setOnClickListener {
            performLogin()
        }
        //lets do the log in
        // first we add a login button
    }
    private fun performLogin(){
        //Lets get input from the user
        val email1: EditText= findViewById(R.id.login_email)
        val password:EditText=findViewById(R.id.login_Password)

        //null checks on inputs
        if(email1.text.isEmpty() || password.text.isEmpty()){
            Toast.makeText(this,"please fill all the fields",Toast.LENGTH_SHORT)
                .show()
            return
        }
        
        val emailinput= email1.text.toString()
        val passwordinput=password.text.toString()

        auth.signInWithEmailAndPassword(emailinput, passwordinput)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success,navigate to the main activity
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)

                    Toast.makeText(
                        baseContext, "Success.",
                        Toast.LENGTH_SHORT
                    ).show()

                } else {
                    // If sign in fails, display a message to the user.

                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()

                }
            }
            .addOnFailureListener {
                Toast.makeText(baseContext, "Authentication failed.${it.localizedMessage}",
                    Toast.LENGTH_SHORT).show()

            }

    }
}