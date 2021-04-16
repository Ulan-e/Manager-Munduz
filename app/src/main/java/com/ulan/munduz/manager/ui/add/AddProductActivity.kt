package com.ulan.munduz.manager.ui.add

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import com.ulan.munduz.manager.data.models.Picture
import com.ulan.munduz.manager.helpers.Constants.Companion.PICK_IMAGE_REQUEST
import com.ulan.app.munduz.helpers.showEditTextEmpty
import com.ulan.app.munduz.helpers.showEmptyDrawable
import com.ulan.app.munduz.helpers.showSuccessProductAdded
import com.ulan.app.munduz.ui.Product
import com.ulan.munduz.manager.R
import com.ulan.munduz.manager.ui.base.BaseActivity
import kotlinx.android.synthetic.main.add_layout.*
import java.io.IOException
import javax.inject.Inject
import android.widget.ArrayAdapter as ArrayAdapter1

class AddProductActivity : BaseActivity(),
    AddView {

    private var mFilePath: Uri? = null
    private var mPicture: Picture = Picture()

    @Inject
    lateinit var mPresenter: AddPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_layout)

        mPresenter.setToolbar()
        mPresenter.initCategory()

        choose_product_image.setOnClickListener {
            mPresenter.chooseImageButtonClicked()
        }

        add_product.setOnClickListener {
            mPresenter.addButtonClicked()
        }
    }

    override fun initToolbar(title: String) {
        setSupportActionBar(product_toolbar)
        supportActionBar?.title = title
        product_toolbar?.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)
        product_toolbar?.setNavigationOnClickListener {
            finish()
        }
    }

    override fun checkForNull() {
        if (product_name.text.toString() == "" || product_desc.text.toString() == "" ||
            product_cost.text.toString() == "" || product_price_for.text.toString() == "") {
            showEditTextEmpty(this)
            return
        }
        if (product_image.drawable == null) {
            showEmptyDrawable(this)
            return
        }
    }

    override fun setCategories(categories: MutableList<String>) {
        val categoryAdapter =
            ArrayAdapter1(this, android.R.layout.simple_spinner_item, categories)
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        product_category.adapter = categoryAdapter
    }

    override fun getInputProduct(): Product {
        val product = Product()
        product.date = System.currentTimeMillis()
        product.category = product_category.selectedItem.toString()
        product.isVisible = true
        product.priceFor = product_price_for.text.toString()
        product.name = product_name.text.toString()
        product.desc = product_desc.text.toString()
        product.cost = Integer.parseInt(product_cost.text.toString())
        product.picture = mPicture
        return product
    }

    override fun clearFields() {
        product_name.text.clear()
        product_desc.text.clear()
        product_cost.text.clear()
        product_price_for.text.clear()
        product_image.setImageResource(android.R.color.transparent)
        showSuccessProductAdded(this)
    }

    override fun chooseImage() {
        intent = Intent()
        intent.setType("image/*")
        intent.setAction(Intent.ACTION_GET_CONTENT)
        startActivityForResult(
            Intent.createChooser(intent, resources.getString(R.string.select_image)),
            PICK_IMAGE_REQUEST
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK
            && data != null && data.data != null) {
            mFilePath = data.data
            try {
                var bitmap: Bitmap = MediaStore.Images.Media.getBitmap(contentResolver, mFilePath)
                product_image.setImageBitmap(bitmap)
                mPicture = mPresenter.getPictureUrl(mFilePath)
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