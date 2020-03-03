package com.imxie.core.util

import android.os.Environment
import com.imxie.core.Core
import com.imxie.core.ktx.util.child
import com.imxie.core.ktx.util.mkdirsIfNeed
import java.io.File

object StoreUtil {

    private fun File?.checkAvailable(): File = this
        ?: throw RuntimeException("Share storage is not currently available.")

    private fun File.ensure(): File = this.apply { mkdirsIfNeed() }
    private fun File.noMedia(): File = this.apply {
        if (isDirectory) {
            File(this, ".nomedia").takeIf { it.exists().not() }?.createNewFile()
        }
    }

    @JvmStatic
    val internalFileDir: File
        get() = Core.context.filesDir

    @JvmStatic
    val internalCacheDir: File
        get() = Core.context.cacheDir

    @JvmStatic
    val externalCacheDir: File
        get() = Core.context.externalCacheDir.checkAvailable().noMedia()

    @JvmStatic
    val videoCacheDir: File?
        get() {
            return try {
                externalCacheDir.child("VideoCache").ensure().noMedia()
            } catch (ignore: Exception) {
                null
            }
        }

    @JvmStatic
    val downloadCacheDir: File
        get() = Core.context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).checkAvailable().noMedia()

    @JvmStatic
    val pictureCacheDir: File
        get() = Core.context.getExternalFilesDir(Environment.DIRECTORY_PICTURES).checkAvailable().noMedia()

    @JvmStatic
    fun getDatabasePath(relativePath: String): File = Core.context.getDatabasePath(relativePath)

}
