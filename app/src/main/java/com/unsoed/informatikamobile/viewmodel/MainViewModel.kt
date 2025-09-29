package com.unsoed.informatikamobile.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unsoed.informatikamobile.data.model.BookDoc
import com.unsoed.informatikamobile.data.network.RetrofitInstance
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _books = MutableLiveData<List<BookDoc>>()
    val books: LiveData<List<BookDoc>> = _books

    fun fetchBooks(query: String) {
        viewModelScope.launch {
            try {

                val response = RetrofitInstance.api.searchBooks( query, limit = 50)

                if (response.isSuccessful) {

                    val result = response.body()?.docs ?: emptyList()
                    _books.value = result
                    Log.d("SUCCESS_GET_DATA", "msg = $result")
                } else {
                    Log.e("API_ERROR", "msg = ${response.code()} ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("API_EXCEPTION", "msg = ${e.localizedMessage ?: "Unknown error"}")
            }
        }
    }
}