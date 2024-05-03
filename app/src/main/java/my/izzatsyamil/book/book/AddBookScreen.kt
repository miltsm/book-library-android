package my.izzatsyamil.book.book

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun AddBookScreen(
    modifier: Modifier
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {  }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Book")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = modifier.padding(innerPadding)
        ) {
//            items(books.value) {book ->
//                Card {
//                    Column {
//                        Text(text = book.author, fontSize = 20.sp, fontWeight = FontWeight.Bold)
//                        Divider()
//                        Text(text = book.title)
//                    }
//                }
//            }
        }
    }
}