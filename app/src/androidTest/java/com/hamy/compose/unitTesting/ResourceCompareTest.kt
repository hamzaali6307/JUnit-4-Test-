package com.hamy.compose.unitTesting

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import com.hamy.compose.unitTesting.utils.ResourceCompare
import org.junit.After
import org.junit.Before
import org.junit.Test

class ResourceCompareTest {

   private lateinit var resourceCompare : ResourceCompare

   @Before
   fun setUp(){
       resourceCompare = ResourceCompare()
   }

    @After
    fun tearDown(){

    }

    @Test
    fun checkingStringMatched() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val result = resourceCompare.resourceComparing(context,
            com.hamy.compose.R.string.app_name,
            "Compose")
        assertThat(result).isTrue()

    }
    @Test
    fun checkingStringDifferent() {

        val context = ApplicationProvider.getApplicationContext<Context>()
        val result = resourceCompare.resourceComparing(context,
            com.hamy.compose.R.string.app_name,
            "Hello")
        assertThat(result).isFalse()

    }
}