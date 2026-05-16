package com.ahmadmaaz1.newsy.presentatoin.home.componet

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ahmadmaaz1.newsy.R
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeDrawerMenu(
    content: @Composable () -> Unit
) {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    var activeSheet by remember { mutableStateOf<String?>(null) }

    ModalNavigationDrawer(
        drawerState = drawerState,

        drawerContent = {

            ModalDrawerSheet(
                modifier = Modifier.width(280.dp)
            )
            {

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Newsy",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 20.dp),
                    color = colorResource(R.color.purple_500)
                )

                Spacer(modifier = Modifier.height(20.dp))

                NavigationDrawerItem(
                    label = {
                        Text("About App")
                    },
                    selected = false,
                    onClick = {
                        activeSheet = "About App"

                        scope.launch {
                            drawerState.close()
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = null
                        )
                    },
                    modifier = Modifier.padding(horizontal = 12.dp)
                )

                NavigationDrawerItem(
                    label = {
                        Text("About Me")
                    },
                    selected = false,
                    onClick = {
                        activeSheet = "About Me"

                        scope.launch {
                            drawerState.close()
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null
                        )
                    },
                    modifier = Modifier.padding(horizontal = 12.dp)
                )

                NavigationDrawerItem(
                    label = {
                        Text("News Channel")
                    },
                    selected = false,
                    onClick = {
                        activeSheet = "News Channel"

                        scope.launch {
                            drawerState.close()
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.List,
                            contentDescription = null
                        )
                    },
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
            }
        }
    ) {

        Scaffold(

            topBar = {

                TopAppBar(

                    title = {
                        Row() {
                            Text(
                                text = "News",
                                color = Color.Black,
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                text = "Y",
                                color = Color.Red,
                                fontWeight = FontWeight.Bold
                            )
                        }

                    },

                    navigationIcon = {

                        IconButton(
                            onClick = {
                                scope.launch {
                                    drawerState.open()
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menu"
                            )
                        }
                    },

                    colors = TopAppBarDefaults.mediumTopAppBarColors(
                        containerColor = Color.Transparent,
                        navigationIconContentColor = colorResource(R.color.purple_500),
                        titleContentColor = colorResource(R.color.purple_500)
                    )
                )
            }

        ) { paddingValues ->

            Box(
                modifier = Modifier.padding(paddingValues)
            ) {
                content()
            }
        }
    }

    // Bottom Sheets

    when (activeSheet) {

        "News Channel" -> {

            NewsChannelSheet(
                onDismiss = {
                    activeSheet = null
                },
                sheetState = bottomSheetState,
                onAddSourceClick = { }
            )
        }

        "About Me" -> {

            AboutMeSheet(
                onDismiss = {
                    activeSheet = null
                },
                sheetState = bottomSheetState
            )
        }

        "About App" -> {

            AboutAppSheet(
                onDismiss = {
                    activeSheet = null
                },
                sheetState = bottomSheetState
            )
        }
    }
}



fun getAppVersion(context: Context): String {
    val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
    val versionName = packageInfo.versionName
    return "Version: $versionName"
}
