package carlos.aleman

import android.app.Activity
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class InternalStorageManager(private val activity: Activity) : FileHandler {
    private val fileName = "user_data.txt"

    override fun SaveInformation(datosAGrabar: Pair<String, String>) {
        try {
            val osw = OutputStreamWriter(activity.openFileOutput(fileName, Activity.MODE_PRIVATE))
            osw.write(datosAGrabar.first + "\n")
            osw.write(datosAGrabar.second + "\n")
            osw.flush()
            osw.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun ReadInformation(): Pair<String, String> {
        try {
            val fis = activity.openFileInput(fileName)
            val isr = InputStreamReader(fis)
            val br = BufferedReader(isr)
            val email = br.readLine() ?: ""
            val clave = br.readLine() ?: ""
            fis.close()
            return email to clave
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return "" to ""
    }
}

