package com.hamy.compose.unitTesting.data.remote

import com.hamy.compose.unitTesting.data.remote.ImageResult


data class ImageResponse(
    val hits: List<ImageResult>,
    val total: Int,
    val totalHits: Int
)