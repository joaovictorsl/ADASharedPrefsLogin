package me.joaovictorsl.practicing

import android.app.Activity
import android.content.Context
import android.text.Editable
import android.widget.Toast
import java.io.*
import java.io.FileOutputStream

var activity: Activity? = null

fun openTextArchive(fileName : String?, context: Context): String? {
    val stringBuilder = StringBuilder()

    try {
        // Abre o arquivo de texto
        val inputStream = context.openFileInput(fileName)
        val inputStreamReader = InputStreamReader(inputStream)
        val bufferedReader = BufferedReader(inputStreamReader)

        // Lê o conteúdo do arquivo linha por linha
        var line: String? = bufferedReader.readLine()
        while (line != null) {
            stringBuilder.append(line)
            line = bufferedReader.readLine()
            if (line != null) {
                stringBuilder.append("\n")
            }
        }

        // Fecha o arquivo e retorna seu conteúdo
        bufferedReader.close()
        inputStreamReader.close()
        inputStream.close()
        return stringBuilder.toString()
    } catch (e: FileNotFoundException) {
        e.printStackTrace()
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return ""

}

fun writeTextArchive(text : String,filePath:String?, context: Context){
    try {
        val outputStream: FileOutputStream =
            context.openFileOutput(filePath, Context.MODE_PRIVATE)
        outputStream.write(text.toByteArray())
        outputStream.close()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}
