package my.izzatsyamil.book

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import my.izzatsyamil.book.book.AddBookBottomSheet
import my.izzatsyamil.book.book.BookViewModel
import my.izzatsyamil.book.book.LibraryContent
import my.izzatsyamil.book.ui.theme.BookTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BookScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookScreen(
    modifier: Modifier = Modifier,
    viewModel: BookViewModel = hiltViewModel()
) {

    val navigator = rememberNavController()
    
    val sheetState = rememberModalBottomSheetState()
    var showAddBookBottomSheet by remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
                 TopAppBar(title = {
                     //hardcoded string for example,
                     // shouldve use string.xml for translation/localisation
                     Text(text = "Simple Book")
                 })
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    //navigator.navigate(ADD_BOOK_DEST)
                    showAddBookBottomSheet = true
                }
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add book")
            }
        }
    ) { innerPadding ->
        NavHost(navController = navigator, startDestination = HOME_DEST) {
            composable(route = HOME_DEST) {
                LibraryContent(
                    modifier = modifier.padding(innerPadding),
                    viewModel = viewModel
                )
            }
    //        composable(route = ADD_BOOK_DEST) {
    //            AddBookScreen(modifier = modifier)
    //        }
        }

        if (showAddBookBottomSheet)
              AddBookBottomSheet(
                  modifier = modifier,
                  sheetState = sheetState,
                  onDismissBottomSheet = {
                      showAddBookBottomSheet = false
                  },
                  viewModel = viewModel
              )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BookTheme {
        BookScreen(Modifier)
    }
}

const val HOME_DEST = "books"
const val ADD_BOOK_DEST = "add"