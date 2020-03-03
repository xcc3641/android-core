package com.imxie.core.ktx.content

import android.content.ClipDescription
import android.content.ClipboardManager


val ClipboardManager.clipText: String?
    get() = primaryClip
        ?.takeIf { it.itemCount > 0 }
        ?.takeIf { it.description.getMimeType(0) == ClipDescription.MIMETYPE_TEXT_PLAIN }
        ?.getItemAt(0)
        ?.text
        ?.toString()