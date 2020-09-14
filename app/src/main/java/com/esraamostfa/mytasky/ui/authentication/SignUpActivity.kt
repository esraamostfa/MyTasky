package com.esraamostfa.mytasky.ui.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import com.esraamostfa.mytasky.R
import com.esraamostfa.mytasky.utils.auth
import com.esraamostfa.mytasky.utils.signIn
import com.esraamostfa.mytasky.utils.toast
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        sign_up_button.setOnClickListener {
            val name = editTextTextPersonName.toString().trim()
            val email = edit_text_email.text.toString().trim()
            val password = edit_text_password.text.toString().trim()
            createAccount(name,email, password)
        }
    }

    override fun onStart() {
        super.onStart()
        auth.currentUser?.let{
            signIn()
        }
    }

    private fun createAccount(name: String, email: String, password: String) {
        if (email.isBlank() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edit_text_email.error = "Please enter valid email"
            edit_text_email.requestFocus()
        }
        if (password.isBlank() || password.length < 6) {
            edit_text_password.error = "6 characters password is required"
            edit_text_password.requestFocus()
        } else {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(SignInActivity.TAG, "createUserWithEmail:success")
                        startActivity(Intent(this, SignInActivity::class.java))

                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(SignInActivity.TAG, "createUserWithEmail:failure", task.exception)
                        toast("Authentication failed.")
                    }
                }
        }
    }
}