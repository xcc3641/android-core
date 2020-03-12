package com.imxie.core.arch

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.imxie.core.R


class FragHubActivity : AppCompatActivity() {

    private var fragmentClass: Class<out Fragment>? = null

    private var fragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_hub)
        fragmentClass = intent.getSerializableExtra(HUB) as? Class<out Fragment>
        fragment = fragmentClass?.newInstance()
        fragment?.apply {
            arguments = intent.extras
            supportFragmentManager.beginTransaction()
                .replace(R.id.lay_container, this)
                .commit()
        }
    }

    companion object {
        const val HUB = "FRAGMENT_HUB"
    }
}