package com.example.live.detail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.live.database.model.Post
import com.example.live.database.model.PostArgType
import com.example.live.detail.DetailRoute
import com.google.gson.Gson

const val DETAIL_POST_INFO = "detail_post_info"
const val DETAIL_ROUTE = "detail_route"

fun NavController.navigateToDetail(detailRoute: String, navOptions: NavOptions? = null) =
    navigate(detailRoute, navOptions)

fun NavGraphBuilder.detailScreen(onBackClick: () -> Unit) {
    composable(
        route = "$DETAIL_ROUTE/{$DETAIL_POST_INFO}",
        arguments = listOf(
            navArgument(DETAIL_POST_INFO) {
                type = PostArgType()
            }
        )
    ) { navBackStackEntry ->
        val post = navBackStackEntry.arguments?.getString(DETAIL_POST_INFO)
            ?.let { Gson().fromJson(it, Post::class.java) }
        DetailRoute(post = post, onBackClick = onBackClick)
    }
}