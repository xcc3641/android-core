package com.imxie.core.arch

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import io.reactivex.rxjava3.core.Maybe

class TransferResult(activity: FragmentActivity) {
    private val transferResultFragment: TransferResultFragment = getTransferResultFragment(activity)

    private fun getTransferResultFragment(activity: FragmentActivity): TransferResultFragment {
        var transferResultFragment = findTransferResultFragment(activity)
        if (transferResultFragment !is TransferResultFragment) {
            transferResultFragment = TransferResultFragment()
            val fragmentManager = activity.supportFragmentManager
            fragmentManager
                .beginTransaction()
                .add(transferResultFragment, TAG)
                .commitAllowingStateLoss()
            fragmentManager.executePendingTransactions()
        }
        return transferResultFragment
    }

    private fun findTransferResultFragment(activity: FragmentActivity): Fragment? {
        return activity.supportFragmentManager.findFragmentByTag(TAG)
    }

    fun startForResult(intent: Intent): Maybe<Bundle> {
        return transferResultFragment.startForResult(intent)
    }

    companion object {
        private const val TAG = "TransferResult"
    }

}
