package com.ulan.munduz.manager.ui.products

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ulan.munduz.manager.helpers.Constants.Companion.PRODUCTS_DATA
import com.ulan.app.munduz.helpers.showEmptyFields
import com.ulan.app.munduz.ui.Product
import com.ulan.munduz.manager.R
import com.ulan.munduz.manager.ui.adapter.ProductsAdapter
import com.ulan.munduz.manager.listeners.OnItemClickListener
import com.ulan.munduz.manager.ui.base.BaseActivity
import com.ulan.munduz.manager.ui.detail.DetailsActivity
import kotlinx.android.synthetic.main.products_activity.*
import javax.inject.Inject

class ProductsActivity : BaseActivity(),
    OnItemClickListener,
    SearchView.OnQueryTextListener,
    MenuItem.OnActionExpandListener,
    ProductsView {

    private lateinit var searchView: SearchView

    @Inject
    lateinit var mPresenter: ProductsPresenter

    @Inject
    lateinit var mAdapter: ProductsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.products_activity)

        mPresenter.loadProducts()
        mPresenter.setToolbarTitle()
    }

    override fun setToolbar(title: String) {
        setSupportActionBar(products_toolbar)
        supportActionBar?.title = title
        products_toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)
        products_toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    override fun showProducts(products: ArrayList<Product>) {
        val layoutManager = LinearLayoutManager(this)
        products_recycler_view.layoutManager = layoutManager
        products_recycler_view.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
        mAdapter.setProducts(products)
        products_recycler_view.adapter = mAdapter
    }

    override fun showNoProducts(message: String) {
        showEmptyFields(this)
    }

    override fun hideProgressBar() {
        products_recycler_view.visibility = View.VISIBLE
        products_progress.visibility = View.GONE
    }

    override fun onItemClick(product: Product?) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra(PRODUCTS_DATA, product)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.manager_menu, menu)
        val searchItem: MenuItem? = menu?.findItem(R.id.search)
        searchView = searchItem?.actionView as SearchView
        searchView.setOnQueryTextListener(this)
        searchView.queryHint = "Search"
        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        mAdapter.filter.filter(query)
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        mAdapter.filter.filter(newText)
        return true
    }

    override fun onMenuItemActionExpand(p0: MenuItem?): Boolean {
        return true
    }

    override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
        return true
    }

    override fun onResume() {
        super.onResume()
        mAdapter.notifyDataSetChanged()
    }

    override fun onStop() {
        super.onStop()
        mPresenter.detachView()

    }
}