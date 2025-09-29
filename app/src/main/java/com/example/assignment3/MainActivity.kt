package com.example.assignment3

import android.R.attr.icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.webkit.Profile
import com.example.assignment3.ui.theme.Assignment3Theme
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Assignment3Theme {

//                    Q1_RowColumnLayout()
//                     Q2_BoxOverlayWithBadge()
//                Q3_LazyColumnWithStickyHeaders()
//                Q4_ScaffoldDemo()
                 Q5_ThemedForm()
            }
        }
    }
}


@Composable
fun Q1_RowColumnLayout() {
    Row(Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .weight(0.25f)
                .background(Color.Gray)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("25%")
        }

        Column(
            modifier = Modifier
                .weight(0.75f),

            ) {
            Box(
                modifier = Modifier
                    .weight(2f)
                    .background(Color.Red)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("2")
            }
            Box(
                modifier = Modifier
                    .weight(3f)
                    .background(Color.Cyan)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("3")
            }
            Box(
                modifier = Modifier
                    .weight(5f)
                    .background(Color.Blue)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("5")
            }
        }
    }
}

@Composable
fun Q2_BoxOverlayWithBadge() {
    var showBadge by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Box {
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Profile picture",
                modifier = Modifier
                    .size(150.dp)

            )

            if (showBadge) {
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .background(Color.Red, CircleShape)
                        .align(Alignment.BottomEnd)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))


        Button(onClick = { showBadge = !showBadge }) {
            Text(if (showBadge) "Hide Badge" else "Show Badge")
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Q3_LazyColumnWithStickyHeaders() {
    val contacts = listOf(
        "Alice", "Aaron", "Ava",
        "Bob", "Bella", "Ben",
        "Charlie", "Cathy", "Chris",
        "David", "Diana", "Derek",
        "Eve", "Ethan", "Emma",
        "Frank", "Fiona", "Fred",
        "George", "Grace", "Gina",
        "Hank", "Holly", "Helen",
        "Ivy", "Ian", "Irene",
        "Jack", "Jill", "James",
        "Kevin", "Kate", "Kara",
        "Leo", "Liam", "Lily",
        "Mike", "Mia", "Mason",
        "Nina", "Noah", "Nora",
        "Oscar", "Olivia", "Owen",
        "Paul", "Pam", "Peter",
        "Quinn", "Queen", "Quincy"
    )
    val groupedContacts = contacts.groupBy { it.first() }
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .padding(10.dp)
    ) {
        LazyColumn(state = listState) {
            groupedContacts.forEach { (letter, group) ->
                stickyHeader() {
                    Text(
                        text = letter.toString(),
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.LightGray)
                            .padding(8.dp)
                    )
                }
                items(group.size) { i ->
                    Text(
                        group[i],
                        Modifier.padding(16.dp)
                    )

                }
            }
        }
        if (listState.firstVisibleItemIndex > 10) {
            FloatingActionButton(
                onClick = {
                    scope.launch {
                        listState.animateScrollToItem(0)
                    }
                },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
            ) {
                Text("Scroll to top")

            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Q4_ScaffoldDemo() {
//Holds the state of the Snackbar (message queue, visibility, etc.)
    val snackbarHostState = remember { SnackbarHostState() }
//Coroutine scope lets us call suspend functions (like showSnackbar) safely
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My app") }
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = true,
                    onClick = { },
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") }

                )
                NavigationBarItem(
                    selected = false,
                    onClick = { },
                    icon = { Icon(Icons.Default.Settings, contentDescription = "Settings") },
                    label = { Text("Settings") }

                )
                NavigationBarItem(
                    selected = false,
                    onClick = { },
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                    label = { Text("Profile") }

                )
            }
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    scope.launch {
                        snackbarHostState.showSnackbar("FAB clicked!")
                    }
                }

            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "add")
            }
        },
//        attach host
        snackbarHost = { SnackbarHost(snackbarHostState) }

    ) { innerPadding ->

        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Hello!")
        }

    }
}

@Composable
fun   Q5_ThemedForm(){

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var error by remember { mutableStateOf<String?>(null) }

    Column (
        modifier= Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement= Arrangement.Center,
        horizontalAlignment= Alignment.CenterHorizontally
    ){
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier= Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier= Modifier.fillMaxWidth()
        )

        if (error!=null){
            Text(
                text = error!!,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding( 8.dp)
            )
        }

        Button(
            onClick = {
                if (username.isEmpty()||password.isEmpty()){
                    error="Please fill out all fields!"
                }
            }
        ) {
            Text("Login")
        }


    }
}





