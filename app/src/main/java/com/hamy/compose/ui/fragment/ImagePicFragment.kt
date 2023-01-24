package com.hamy.compose.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.hamy.compose.R
import com.hamy.compose.unitTesting.viewModel.ShoppingViewModel

class ImagePicFragment : Fragment(R.layout.add_shoppin_item) {
    private val viewModel: ShoppingViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}