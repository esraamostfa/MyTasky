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
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : AppCompatActivity() {

    companion object {
        const val TAG = "CREATE_USER_FAILURE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        //click sign in button
        sign_in_button.setOnClickListener {
            val email = edit_text_email.text.toString().trim()
            val password = edit_text_password.text.toString().trim()
            try {
                signInUser(email, password)
            } catch (e: Exception){
                Log.e("SIGNIN", "error")
            }
        }

        go_to_sign_up.setOnClickListener{
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        auth.currentUser?.let {
            signIn()
        }
    }

    private fun signInUser(email: String, password: String) {
        if (email.isBlank() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edit_text_email.error = "Please enter valid email"
            edit_text_email.requestFocus()
        }
        if (password.isBlank() || password.length < 6) {
            edit_text_password.error = "6 characters password is required"
            edit_text_password.requestFocus()
        } else {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success")
                        signIn()
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        toast("Authentication failed!")
                    }
                }
        }
    }
}