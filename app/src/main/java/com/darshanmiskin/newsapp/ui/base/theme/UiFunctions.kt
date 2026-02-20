package com.darshanmiskin.newsapp.ui.base.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidthIn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil3.compose.AsyncImage
import com.darshanmiskin.newsapp.R
import com.darshanmiskin.newsapp.data.model.network.Article
import com.darshanmiskin.newsapp.data.model.network.Source


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
fun NewsItem(onClick: (String) -> Unit, article: Article) {
    Spacer(Modifier.padding(4.dp))
    ConstraintLayout {
        val (image, title, source, desc, gradient) = createRefs()
        createHorizontalChain(title, source, chainStyle = ChainStyle.SpreadInside)
        AsyncImage(
            model = article.url,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .constrainAs(image) {
                    top.linkTo(parent.top)
                }
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            colorResource(R.color.gradient_start_color),
                            colorResource(R.color.gradient_end_color)
                        )
                    )
                )
                .constrainAs(gradient) {
                    top.linkTo(title.top)
                    bottom.linkTo(parent.bottom)
                    height = Dimension.fillToConstraints
                }
        )
        Text(
            text = article.description,
            color = Color.White,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .constrainAs(desc) {
                    bottom.linkTo(parent.bottom)
                }
        )
        Text(
            text = article.title,
            color = Color.White,
            fontSize = 20.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(top = 12.dp)
                .padding(horizontal = 12.dp)
                .constrainAs(title) {
                    bottom.linkTo(desc.top)
                    width = Dimension.fillToConstraints
                }
        )
        Text(
            text = article.source.name,
            color = Color.White,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .requiredWidthIn(50.dp, 100.dp)
                .padding(12.dp)
                .constrainAs(source) {
                    baseline.linkTo(title.baseline)
                    end.linkTo(parent.end)
                    start.linkTo(title.end)
                }
        )
    }
}

@Composable
fun SimpleListItem(onClick: (String) -> Unit, code: String, name: String, backgroundColor: Color = Teal700) {
    Spacer(Modifier.padding(4.dp))
    TextButton(
        { onClick(code) },
        modifier = Modifier
            .background(backgroundColor, shape = RoundedCornerShape(25))
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
//    SimpleListItem({}, "", "")
    val article = Article(
        author = "Author",
        content = "content of the article",
        description = "desc of the article",
        publishedAt = "30th June",
        source = Source("id", "Source akljdfka jadffakf"),
        title = "title alkjdfkaj fafj ajdfka fkajfk adkfja aljdfklajf afddas d",
        url = "url",
        urlToImage = "ImageUrl"
    )
    NewsItem({}, article)
}