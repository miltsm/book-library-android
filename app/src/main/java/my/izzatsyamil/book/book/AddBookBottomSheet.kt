package my.izzatsyamil.book.book

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddBookBottomSheet(
    modifier: Modifier,
    sheetState: SheetState,
    onDismissBottomSheet: () -> Unit,
    viewModel: BookViewModel
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    var bookTitleState by remember {
        mutableStateOf("")
    }
    var authorState by remember {
        mutableStateOf("")
    }

    ModalBottomSheet(
        onDismissRequest = onDismissBottomSheet,
        sheetState = sheetState
    ) {

        Column(
            modifier =  modifier.align(
                Alignment.CenterHorizontally
            )
        ) {
            TextField(
                modifier = modifier
                    //.fillMaxWidth()
                    .padding(bottom = 8.dp),
                value = bookTitleState,
                onValueChange = { bookTitleState = it },
                label = {
                    Text(text = "Book title")
                }
            )
            TextField(
                modifier = modifier
                    //.fillMaxWidth()
                    .padding(vertical = 8.dp),
                value = authorState,
                onValueChange = { authorState = it },
                label = {
                    Text(text = "Author's name")
                }
            )
            Button(
                modifier = modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 30.dp),
                //enabled = bookTitleState.value.isNotBlank() && authorState.value.isNotBlank(),
                onClick = {
                    if (bookTitleState.isNotBlank() && authorState.isNotBlank()) {
                        viewModel.addBook(authorState, bookTitleState)
                        onDismissBottomSheet()
                    } else
                        Toast.makeText(context, "Invalid inputs", Toast.LENGTH_SHORT).show()
                }
            ) {
                Text(text = "ADD BOOK")
            }
        }
    }
}