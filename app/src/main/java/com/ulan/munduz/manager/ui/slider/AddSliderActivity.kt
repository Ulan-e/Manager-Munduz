package com.ulan.munduz.manager.ui.slider

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import com.ulan.munduz.manager.helpers.Constants
import com.ulan.munduz.manager.helpers.Constants.Companion.PICK_IMAGE_REQUEST
import com.ulan.munduz.manager.R
import com.ulan.munduz.manager.ui.base.BaseActivity
import kotlinx.android.synthetic.main.add_layout.*
import kotlinx.android.synthetic.main.add_slider_layout.*
import java.io.IOException
import javax.inject.Inject

class AddSliderActivity : BaseActivity(), AddSliderView {

    @Inject
    lateinit var mPresenter: AddSliderPresenter

    private var mFilePath: Uri? = null
    private var IMAGE_POSITION: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_slider_layout)
        mPresenter.setToolbar()
        clickHandle()
    }

    private fun clickHandle(){
        first_button.setOnClickListener {
            IMAGE_POSITION = 1
            chooseImage()
            mPresenter.insertToDatabase("first")
        }

        second_button.setOnClickListener {
            IMAGE_POSITION = 2
            chooseImage()
            mPresenter.insertToDatabase("second")

        }

        third_button.setOnClickListener {
            IMAGE_POSITION = 3
            chooseImage()
            mPresenter.insertToDatabase("third")
        }

        fourth_button.setOnClickListener {
            IMAGE_POSITION = 4
            chooseImage()
            mPresenter.insertToDatabase("fourth")
        }
    }

    private fun showImages(bitmap: Bitmap) {
        when (IMAGE_POSITION) {
            1 -> first_image.setImageBitmap(bitmap)
            2 -> second_image.setImageBitmap(bitmap)
            3 -> third_image.setImageBitmap(bitmap)
            4 -> fourth_image.setImageBitmap(bitmap)
            else -> fourth_image.setImageBitmap(null)
        }
    }

    override fun showToolbar() {
        setSupportActionBar(slider_toolbar)
        supportActionBar?.title = "Слайдер"
        product_toolbar?.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)
        product_toolbar?.setNavigationOnClickListener {
            finish()
        }
    }

    override fun chooseImage() {
        intent = Intent()
        intent.setType("image/*")
        intent.setAction(Intent.ACTION_GET_CONTENT)
        startActivityForResult(
            Intent.createChooser(intent, resources.getString(R.string.select_image)),
            Constants.PICK_IMAGE_REQUEST
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK
            && data != null && data.data != null
        ) {
            mFilePath = data.data
            try {
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, mFilePath)
                mPresenter.setSliderImage(mFilePath)
                showImages(bitmap)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }
}