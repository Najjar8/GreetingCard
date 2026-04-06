package com.example.greetingcard.designsystem

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.greetingcard.data.AppDatabase
import com.example.greetingcard.data.TaskEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch



class TaskViewModel(application: Application) : AndroidViewModel(application){

    private val taskDao = AppDatabase.getInstance(application).taskDao()

    val tasks: StateFlow<List<TaskEntity>> = taskDao
        .getAllTasks()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addTask(title: String, description: String, dueDate: String) {

        if (title.isBlank()) return

        viewModelScope.launch {
                taskDao.insertTask(
                    TaskEntity(
                        title = title,
                        description = description,
                        isCompleted = false,
                        dueDate = dueDate
                    )
                )
        }
    }

    fun toggleTask(task: TaskEntity) {
        viewModelScope.launch {
            taskDao.updateTask(task.copy(isCompleted = !task.isCompleted))
        }
    }

}