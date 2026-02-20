package com.darshanmiskin.newsapp.ui.base.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.darshanmiskin.newsapp.R
import com.darshanmiskin.newsapp.data.model.network.Article


@Composable
fun Loading() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(), contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun Error(retryClick: () -> Unit) {
    Box(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(R.string.oops), fontSize = 24.sp)
            Spacer(Modifier.padding(8.dp))
            Text(text = stringResource(R.string.something_went_wrong_let_s_try_once_again))
            Spacer(Modifier.padding(16.dp))
            Button(onClick = retryClick) {
                Text(text = stringResource(R.string.try_again))
            }
        }
    }
}

@Composable
fun NoDataFound() {
    Box(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(), contentAlignment = Alignment.Center
    ) {
        Text(text = stringResource(R.string.no_data_found), fontSize = 24.sp)
    }
}

@Composable
fun NewsItem(onClick: (String) -> Unit) {
    Spacer(Modifier.padding(4.dp))
    ConstraintLayout {

    }
}

@Composable
fun SimpleListItem(onClick: (String) -> Unit, code: String, name: String) {
    Spacer(Modifier.padding(4.dp))
    TextButton(
        { onClick(code) },
        modifier = Modifier
            .background(Teal700, shape = RoundedCornerShape(25))
            .fillMaxWidth(),
        shape = RoundedCornerShape(corner = CornerSize(15.dp))
    ) {
        Text(name, color = Color.White)
    }
    Spacer(Modifier.padding(4.dp))
}

@Preview
@Composable
fun Preview() {
//    NoDataFound()
//    Loading()
//    Error({})
    SimpleListItem({}, "", "")
}