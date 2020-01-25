package com.example.bolobolo
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class ActivityLogin : AppCompatActivity() {
    val auth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkLogin()
        LoginBtn.setOnClickListener { logIn() }
        RegisterBtn.setOnClickListener { startActivity(Intent(this, RegisterActivity::class.java)) }
    }

    private fun logIn()
    {
        val email = etMail.text.toString()
        val password = etPassword.text.toString()

        if(email.isNotEmpty() && password.isNotEmpty())
        {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if(it.isSuccessful)
                    {
                        Toast.makeText(this, "SignIn is successful.", Toast.LENGTH_LONG).show()
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_LONG).show()
                    }
                }
        } else {
            Toast.makeText(this, "Must Be filled!", Toast.LENGTH_LONG).show()
        }
    }

    private fun checkLogin()
    {
        if(auth.currentUser != null){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}
