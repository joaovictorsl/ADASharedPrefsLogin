package me.joaovictorsl.practicing

import android.content.Context
import android.text.Editable
import android.widget.Toast
import java.io.*
import java.io.FileOutputStream

fun openTextArchive(fileName : String, context: Context): String? {
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

fun writeTextArchive(text : String, context: Context){
    try {
        val outputStream: FileOutputStream =
            context.openFileOutput("myNotes.txt", Context.MODE_PRIVATE)
        outputStream.write(text.toByteArray())
        outputStream.close()
        Toast.makeText(context.applicationContext, "Notes saved succesfully!", Toast.LENGTH_SHORT)
            .show()
    } catch (e: Exception) {
        e.printStackTrace()
        Toast.makeText(context.applicationContext, "Error saving notes  ", Toast.LENGTH_SHORT)
            .show()
    }
}
