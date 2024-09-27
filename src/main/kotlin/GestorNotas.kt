package org.example

import java.io.BufferedReader
import java.io.File
import java.nio.file.Files
import java.nio.file.Path

class GestorNotas(private val fileRoute: Path) {

    fun transform(file: File): List<MutableMap<String, String>> {

        //Comprobar si el fichero existe
        if (Files.notExists(fileRoute)) {
            Files.createDirectory(fileRoute.parent)
            Files.createFile(fileRoute)
        }

        val listUsers = mutableListOf<MutableMap<String, String>>()
        val users = mutableMapOf<String, String>()

        val isFirstLine = listOf("Apellidos","Nombre","Asistencia","Parcial1","Parcial2","Ordinario1","Ordinario2","Practicas","OrdinarioPracticas")

        //Leer fichero
        val br: BufferedReader = Files.newBufferedReader(fileRoute)
        br.use {
            it.forEachLine { line ->
                val userData = line.split(";")
                for (i in userData.indices) {
                    users[isFirstLine[i]] = userData[i]
                }
                listUsers.add(users)
            }
        }

        return listUsers.sortedBy { it["Apellidos"] }
    }

    fun calculateFinalGrade(studentList: List<MutableMap<String, String>>) {





    }

    fun calculateGrade(studentList: MutableList<Map<String, List<String>>>) {



    }

    fun gradesManager(studentList: List<Map<String, Int>>): Pair<List<String>,List<String>> {
        TODO("Que devuelva dos listas y que una sea Aprobados y otra suspensos")
    }
}