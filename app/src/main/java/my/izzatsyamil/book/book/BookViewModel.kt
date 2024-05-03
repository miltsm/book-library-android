package my.izzatsyamil.book.book

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import my.izzatsyamil.book.data.Book
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(
    //use cases or repos
) : ViewModel() {

    companion object {
        const val TAG = "BOOK_VM"
    }

    private val _books = MutableStateFlow(
        mutableListOf<Book>(
            Book(100L, "J.R.R Tolkien", "The Fellowship of the Ring"),
            Book(101L, "J.R.R Tolkien", "The Two Towers"),
            Book(101L, "J.R.R Tolkien", "The Return of the King")
        )
    )
    val books : StateFlow<List<Book>> = _books.asStateFlow()

    fun addBook(
        author: String, title: String
    ) = viewModelScope.launch {
//        Log.d(TAG, "author: $author\ntitle: $title")

        if (author.isNotEmpty() && title.isNotEmpty())
            _books.apply {
                value = books.value.toMutableList().also { bks ->
                    bks.add(
                        Book(System.currentTimeMillis(), author, title)
                    )
                }

//                Log.d(TAG, "${books.value}")
            }
    }

    fun deleteBook() {

    }
}