package my.izzatsyamil.book.book

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import my.izzatsyamil.book.data.Book
import my.izzatsyamil.book.ui.theme.BookTheme

@Composable
fun LibraryContent(
    modifier: Modifier,
    viewModel: BookViewModel
) {
    val books by viewModel.books.collectAsStateWithLifecycle()

    val listState = rememberLazyGridState()

    Box(
        modifier = modifier.fillMaxSize()
    ) {

        if (books.isEmpty())
            Text(
                modifier = modifier
                    .align(Alignment.Center),
                text = "No book for now :("
            )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            state = listState
        ) {
            items(books) { book ->
                BookCard(book)
            }
        }
    }
}

@Composable
fun BookCard(
    book: Book,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(8.dp)
    ) {
        Column(
            modifier = modifier
                .defaultMinSize(minWidth = 150.dp, minHeight = 190.dp)
//                .height(IntrinsicSize.Max)
                .padding(8.dp)
                //.background(Color.Magenta)
        ) {
            Text(text = book.title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = modifier.weight(1f))
            Text(text = "by", fontSize = 12.sp)
            Text(text = book.author, fontSize = 16.sp, fontStyle = FontStyle.Italic)
        }
    }
}

@Preview
@Composable
fun PreviewBookCard() {
    BookTheme {
        BookCard(
            book = Book(0L, "J.R.R Tolkien", "The Fellowship of the Ring"),
            Modifier
        )
    }
}

@Preview
@Composable
fun PreviewBooksScreen() {
    BookTheme {
        LibraryContent(modifier = Modifier, hiltViewModel())
    }
}