package com.esraamostfa.mytasky.ui.tasks.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.esraamostfa.mytasky.R
import com.esraamostfa.mytasky.model.Task
import com.esraamostfa.mytasky.utils.tasksRef
import kotlinx.android.synthetic.main.fragment_add_task_dialog.*

class AddTaskDialogFragment() : DialogFragment() {

    private var taskAddedListener: TaskAddedListener? = null
    private lateinit var taskTitle : String
    private lateinit var taskDetails : String
    private lateinit var dialogView : View

    interface TaskAddedListener {
        fun onDialogPositiveClick(title: String, details: String)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        dialogView = inflater.inflate(R.layout.fragment_add_task_dialog, container)
        return dialogView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        save_task_button.setOnClickListener {
            taskTitle = editText_task_title.text.toString().trim()
            taskDetails = editText_task_details.text.toString().trim()
            val taskId = tasksRef.push().key
            addTask(taskId!!, taskTitle, taskDetails)
            //taskAddedListener?.onDialogPositiveClick(taskTitle, taskDetails)
            dialog?.dismiss()
        }
    }

    private fun addTask(taskId: String, title: String, details: String) {
        val task = Task(taskId, title, details)
        tasksRef.child(taskId).setValue(task)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT)
    }

    fun setTaskAddedListener(listener: TaskAddedListener) {
        taskAddedListener = listener
    }


    //Override the Fragment.onAttach() method to instantiate the AddTaskDialogListener
    /*override fun onAttach(context: Context) {
        super.onAttach(context)
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            taskAddedListener = context as  TaskAddedListener
        } catch (e: ClassCastException) {
            // The activity doesn't implement the interface, throw exception
            throw ClassCastException((context.toString() +
                    " must implement NoticeDialogListener"))
        }
    }*/

}