package carlos.aleman

import android.app.Activity
import android.content.Context
import androidx.core.content.edit

class SharedPreferencesManager (val actividad: Activity): FileHandler{
    override fun SaveInformation(datosAGrabar:Pair<String,String>){
        val sharedPref = actividad.getPreferences(Context.MODE_PRIVATE)
        sharedPref.edit {
            putString(LOGIN_KEY, datosAGrabar.first)
            putString(PASSWORD_KEY, datosAGrabar.second)
        }
    }
    override fun ReadInformation():Pair<String,String>{
        val sharedPref = actividad.getPreferences(Context.MODE_PRIVATE)
        val email = sharedPref.getString(LOGIN_KEY,"").toString()
        val clave = sharedPref.getString(PASSWORD_KEY,"").toString()
        return (email to clave)
    }
}
