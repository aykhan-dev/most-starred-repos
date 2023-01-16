package ev.aykhn.presentation.ui.flow.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ev.aykhn.domain.usecase.MostStarredReposUseCase
import ev.aykhn.presentation.ui.flow.main.screens.mostStarredRepos.MostStarredReposScreen
import ev.aykhn.presentation.ui.theme.AppTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                     MainFlow()
                }
            }
        }
    }
}

@Composable
fun MainFlow() {
    AppTheme {
        MainFlowRouter()
    }
}

@Composable
fun MainFlowRouter() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "/") {
        composable("/") { MostStarredReposScreen(navController) }
    }
}