package com.esraamostfa.mytasky.model

import android.util.Log
import com.esraamostfa.mytasky.utils.tasksRef
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue

object MyTaskyStoare {

    fun getTasks(): MutableList<Task> {
        val tasks = mutableListOf<Task>()
        tasksRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                tasks.clear()
                for (taskSnapshot in snapshot.children)
                    if (taskSnapshot.getValue<Task>() != null) {
                        tasks.add(taskSnapshot.getValue<Task>() as Task)
                    }
            }
            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("TasksActivity", "Failed to read value.", error.toException())
            }
        })
        return tasks
    }
}