package com.esraamostfa.mytasky.utils

import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.esraamostfa.mytasky.ui.tasks.TasksActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

val auth = FirebaseAuth.getInstance()

val database = Firebase.database
val tasksRef = database.getReference("tasks")
val NOTASKSTAG = "No tasks yet!"


fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.signIn() {
    startActivity(Intent(this, TasksActivity::class.java))
}
