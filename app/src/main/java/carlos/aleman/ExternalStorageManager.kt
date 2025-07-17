package carlos.aleman

import android.app.Activity
import android.os.Environment
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException

class ExternalStorageManager(private val activity: Activity) : FileHandler {
    private val fileName = "user_data.txt"

    private fun isExternalStorageWritable(): Boolean {
        return Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
    }

    private fun isExternalStorageReadable(): Boolean {
        return Environment.getExternalStorageState() in setOf(Environment.MEDIA_MOUNTED, Environment.MEDIA_MOUNTED_READ_ONLY)
    }

    override fun SaveInformation(datosAGrabar: Pair<String, String>) {
        if (isExternalStorageWritable()) {
            try {
                val file = File(activity.getExternalFilesDir(null), fileName)
                val fos = FileOutputStream(file)
                fos.write(datosAGrabar.first.toByteArray())
                fos.write("\n".toByteArray())
                fos.write(datosAGrabar.second.toByteArray())
                fos.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    override fun ReadInformation(): Pair<String, String> {
        if (isExternalStorageReadable()) {
            try {
                val file = File(activity.getExternalFilesDir(null), fileName)
                if (!file.exists()) return "" to ""
                val fis = FileInputStream(file)
                val bytes = ByteArray(fis.available())
                fis.read(bytes)
                fis.close()
                val text = String(bytes)
                val lines = text.split("\n")
                val email = if (lines.isNotEmpty()) lines[0] else ""
                val clave = if (lines.size > 1) lines[1] else ""
                return email to clave
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return "" to ""
    }
}

