package ev.aykhn.presentation.ui.flow.main.screens.mostStarredRepos

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ev.aykhn.presentation.ui.component.RepoItem
import ev.aykhn.presentation.ui.theme.AppTheme

@Composable
fun MostStarredReposScreen(
    navController: NavController,
    viewModel: MostStarredReposViewModel = hiltViewModel(),
) {
    val uiState by viewModel.state.collectAsState()
    val repos by viewModel.repos.collectAsState(initial = emptyList())

    val scaffoldState = rememberScaffoldState()
    val lazyListState = rememberLazyListState()

    val errorMessage = uiState.errorMessage

    lazyListState.OnBottomReached {
        viewModel.onEvent(MostStarredReposEvents.Fetch)
    }

    // business logic broker errors
    LaunchedEffect(errorMessage) {
        if (errorMessage != null) {
            scaffoldState.snackbarHostState.showSnackbar(errorMessage)
            viewModel.clearErrorMessage()
        }
    }

    Scaffold(scaffoldState = scaffoldState) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            state = lazyListState,
            contentPadding = PaddingValues(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            items(items = repos, key = { it.id }) { item ->
                RepoItem(repo = item)
                Spacer(modifier = Modifier.height(16.dp))
            }
            if (uiState.isLoading) {
                loading()
            }
        }
    }
}

@Preview
@Composable
fun MostStarredReposScreenPreview() {
    val navController = rememberNavController()

    AppTheme {
        MostStarredReposScreen(navController = navController)
    }
}

// predefined loading UI component for lazy list scope

private fun LazyListScope.loading() {
    item {
        CircularProgressIndicator(modifier = Modifier.padding(16.dp))
    }
}

@Composable
fun LazyListState.OnBottomReached(
    loadMore: () -> Unit
) {
    val shouldLoadMore = remember {
        derivedStateOf {
            val lastVisibleItem = layoutInfo.visibleItemsInfo.lastOrNull()
                ?: return@derivedStateOf true

            lastVisibleItem.index == layoutInfo.totalItemsCount - 1
        }
    }

    // Convert the state into a cold flow and collect
    LaunchedEffect(shouldLoadMore) {
        snapshotFlow { shouldLoadMore.value }
            .collect {
                // if should load more, then invoke loadMore
                if (it) loadMore()
            }
    }
}