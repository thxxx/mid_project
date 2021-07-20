package com.example.hostagram

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import com.example.hostagram.databinding.ActivityLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginActivity : AppCompatActivity() {
    var auth : FirebaseAuth? = null
    var googleSigninClient : GoogleSignInClient? = null // 구글 로그인을 위한 클래스
    var GOOGLE_LOGIN_CODE = 9001

    private lateinit var binding : ActivityLoginBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.emailLogin.setOnClickListener{
            signinAndSignup()
        }

        var gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("615691424940-985nukiquhi13ursv3bhdkoe7cc850kl.apps.googleusercontent.com")
            .requestEmail()
            .build() // build로 닫아줘야한다.
        googleSigninClient = GoogleSignIn.getClient(this, gso)
        binding.googleSignInButton.setOnClickListener {
            // First step 총 3단계 중에서 첫번째 단계이다!
            googleLogin()
        }

        auth = FirebaseAuth.getInstance()
    }
    fun googleLogin(){
        var signInIntent = googleSigninClient?.signInIntent
        startActivityForResult(signInIntent, GOOGLE_LOGIN_CODE)
    }

    private val activityResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                result ->
            if(result.resultCode == RESULT_OK){

            }
            else {

            }
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