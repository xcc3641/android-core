package com.imxie.core.arch

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.subjects.MaybeSubject

class TransferResultFragment : Fragment() {

    private lateinit var subject: MaybeSubject<Bundle>
    private val requestCode = 233

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    fun startForResult(intent: Intent): Maybe<Bundle> {
        subject = MaybeSubject.create()
        return subject.doOnSubscribe {
            startActivityForResult(intent, requestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        subject.apply {
            if (resultCode == Activity.RESULT_OK) {
                onSuccess(data?.extras ?: Bundle())
            } else {
                onComplete()
            }
        }
    }
}
