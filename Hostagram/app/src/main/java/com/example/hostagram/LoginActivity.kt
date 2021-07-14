package com.example.hostagram

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.example.hostagram.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginActivity : AppCompatActivity() {
    var auth : FirebaseAuth? = null

    private lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.emailLogin.setOnClickListener{
            signinAndSignup()
        }

        auth = FirebaseAuth.getInstance()
    }
    fun signinAndSignup(){
        auth?.createUserWithEmailAndPassword(binding.emailEdittext.text.toString(),
            binding.passwordEdittext.text.toString())?.addOnCompleteListener {
                task -> if(task.isSuccessful){
                    // Creating a user account
                    moveMainPage(task.result?.user) // 여기에 USER 정보가 담긴다.
                }else if(!task.exception?.message.isNullOrEmpty()){
                    //Print simple login error message
                    Toast.makeText(applicationContext, task.exception?.message, Toast.LENGTH_LONG).show()
                }else{
                    // Go Login if you have account
                    signinEmail()
                }
            }
    }

    fun signinEmail(){
        auth?.signInWithEmailAndPassword(findViewById<EditText>(R.id.email_edittext).text.toString(),
            findViewById<EditText>(R.id.password_edittext).text.toString())?.addOnCompleteListener {
                task -> if(task.isSuccessful){
            // login
            moveMainPage(task.result?.user) // 여기에 USER 정보가 담긴다.

        }else{
            // if login info is wrong
            Toast.makeText(applicationContext, task.exception?.message, Toast.LENGTH_LONG).show()

        }
        }
    }

    fun moveMainPage(user: FirebaseUser?){
        if(user != null){
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
        }
    }

}