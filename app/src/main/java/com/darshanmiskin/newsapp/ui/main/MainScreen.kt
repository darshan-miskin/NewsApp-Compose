package com.darshanmiskin.newsapp.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.darshanmiskin.newsapp.ui.main.model.MenuItem
import kotlin.collections.forEach


@Composable
fun MainScreen(mainMenu: List<MenuItem>, paddingValues: PaddingValues) {
    Surface(Modifier.padding(paddingValues)) {
        Column(modifier = Modifier.padding(8.dp)) {
            mainMenu.forEach { menu ->
                Button(
                    shape = RoundedCornerShape(10),
                    onClick = menu.listener,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(vertical = 8.dp)
                ) {
                    Text(
                        text = menu.title
                    )
                }
            }
        }
    }
}
