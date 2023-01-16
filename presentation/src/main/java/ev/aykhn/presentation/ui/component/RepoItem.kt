package ev.aykhn.presentation.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import ev.aykhn.domain.model.Repo
import ev.aykhn.presentation.R
import ev.aykhn.presentation.ui.theme.AppTheme

// item design of repositories list in most starred repositories screen

@Composable
fun RepoItem(modifier: Modifier = Modifier, repo: Repo) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = MaterialTheme.shapes.medium,
        elevation = 8.dp,
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 16.dp, horizontal = 20.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = repo.name ?: stringResource(R.string.label_unknown),
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .weight(1f)
                        .fillMaxWidth()
                )

                Image(
                    painter = rememberAsyncImagePainter(repo.userAvatarUrl),
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape),
                    contentDescription = ""
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = buildAnnotatedString {
                        append(stringResource(R.string.label_created_by))
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append(repo.username ?: stringResource(id = R.string.label_unknown))
                        }
                        append(stringResource(R.string.separator))
                    },
                    style = MaterialTheme.typography.overline.copy(color = Color.Gray),
                    modifier = Modifier.padding(top = 4.dp)
                )
                Icon(
                    imageVector = Icons.Rounded.Star,
                    contentDescription = "",
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .size(12.dp),
                    tint = Color.Magenta,
                )
                Text(
                    text = buildAnnotatedString {
                        append(stringResource(R.string.label_stargazers))
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold
                            )
                        )
                        {
                            append(
                                repo.starCount?.toString()
                                    ?: stringResource(id = R.string.label_unknown)
                            )
                        }
                    },
                    style = MaterialTheme.typography.overline.copy(color = Color.Gray),
                    modifier = Modifier.padding(top = 4.dp, start = 2.dp)
                )
            }
            Text(
                text = repo.description ?: stringResource(id = R.string.label_unknown),
                style = MaterialTheme.typography.body2,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
            )
        }
    }
}

@Preview
@Composable
fun RepoItemPreview() {
    val repo = Repo(
        id = 1,
        name = "Dummy Repo",
        description = "You should create the NavController in the place in your composable hierarchy where all composables that need to reference it have access to it.",
        starCount = 10,
        username = "dummy-username",
        userAvatarUrl = "Dummy user avatar url",
        pageIndex = 1,
    )

    AppTheme {
        Surface(color = MaterialTheme.colors.background) {
            RepoItem(modifier = Modifier.padding(16.dp), repo = repo)
        }
    }
}