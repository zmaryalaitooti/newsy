package com.ahmadmaaz1.newsy.presentatoin.home.componet

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ahmadmaaz1.newsy.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarMenu() {

    TopAppBar(
        title = { Text(text = "") },
        modifier = Modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color.Transparent,
            actionIconContentColor = colorResource(R.color.purple_500),
            navigationIconContentColor = colorResource(R.color.purple_500)
        ),
        actions = {
            val menuItems = listOf( "About App","About Me","News Channel")
            var isMenuExpanded by remember { mutableStateOf(false) }
            val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
            var activeSheet by remember { mutableStateOf<String?>(null) }

            // 3-dot icon
            IconButton(onClick = { isMenuExpanded = true }) {
                Icon(Icons.Default.MoreVert, contentDescription = "Menu")
            }

            // Dropdown menu
            DropdownMenu(
                expanded = isMenuExpanded,
                onDismissRequest = { isMenuExpanded = false }
            ) {
                menuItems.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(item) },
                        onClick = {
                            activeSheet = item
                            isMenuExpanded = false
                        }
                    )
                }
            }

            // Show selected bottom sheet
            when (activeSheet) {
                "News Channel" -> {
                    NewsChannelSheet(
                        onDismiss = { activeSheet = null },
                        sheetState = bottomSheetState
                    )
                }

                "About Me" -> {
                    AboutMeSheet(
                        onDismiss = { activeSheet = null },
                        sheetState = bottomSheetState
                    )
                }

                "About App" -> {
                    AboutAppSheet(
                        onDismiss = { activeSheet = null },
                        sheetState = bottomSheetState
                    )
                }
            }
        }
    )
}




fun getAppVersion(context: Context): String {
    val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
    val versionName = packageInfo.versionName
    return "Version: $versionName"
}
