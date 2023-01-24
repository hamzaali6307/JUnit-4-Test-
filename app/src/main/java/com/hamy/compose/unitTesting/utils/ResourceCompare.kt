package com.hamy.compose.unitTesting.utils

import android.content.Context

class ResourceCompare {

    fun resourceComparing(context: Context,resId:Int,string: String) :Boolean{
        return context.getString(resId) == string
    }
}