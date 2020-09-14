package com.esraamostfa.mytasky.ui.tasks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.esraamostfa.mytasky.R
import com.esraamostfa.mytasky.model.MyTaskyStoare
import com.esraamostfa.mytasky.ui.tasks.dialog.AddTaskDialogFragment
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_tasks.*

class TasksActivity : AppCompatActivity() //AddTaskDialogFragment.TaskAddedListener
{

    private lateinit var adapter: TasksRecyclerviewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tasks)

        setRecyclerView()
        addTask()
        updateUi()

        val user = Firebase.auth.currentUser
        getUserData(user)
    }

    private fun addTask() {
        add_task_button.setOnClickListener {
            showAddTaskDialog()
        }
    }

    private fun setRecyclerView() {
        adapter = TasksRecyclerviewAdapter()
        val layoutManager = LinearLayoutManager(this)
        tasks_recyclerview.adapter = adapter
        tasks_recyclerview.layoutManager = layoutManager
    }

    private fun showAddTaskDialog() {
        val dialog = AddTaskDialogFragment()
        //dialog.setTaskAddedListener(this)
        dialog.show(supportFragmentManager, "AddTaskDialogFragment")
    }

    private fun updateUi() {
        val tasks = MyTaskyStoare.getTasks()
        adapter.updateTasks(tasks)
    }

    // The dialog fragment receives a reference to this Activity through the
    // Fragment.onAttach() callback, which it uses to call the following methods
    // defined by AddTaskDialogListener interface
    /*override fun onDialogPositiveClick(title: String, details: String) {
        val task = Task("", title, details)
        Toast.makeText(this, title, Toast.LENGTH_SHORT).show()
        tasks.add(task);
        adapter.updateTasks(tasks)
    }
*/

    private fun getUserData(user: FirebaseUser?) {
        user?.let {
            // Name, email address, and profile photo Url
            val name = user.displayName
            val email = user.email
            //val photoUrl = user.photoUrl

            // Check if user's email is verified
            val emailVerified = user.isEmailVerified

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getToken() instead.
            val userId = user.uid

            //name_text_view.text = name
            //email_text_view.text = email
            //email_verification_text_view.text = emailVerified.toString()
            //user_id_text_view.text = userId
        }
    }

}