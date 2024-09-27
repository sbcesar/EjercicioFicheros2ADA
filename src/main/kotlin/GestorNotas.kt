package org.example

import java.io.BufferedReader
import java.io.File
import java.nio.file.Files
import java.nio.file.Path

class GestorNotas(private val fileRoute: Path) {

    fun transform(file: File): List<Map<String, List<String>>> {

        //Comprobar si el fichero existe
        if (Files.notExists(fileRoute)) {
            Files.createDirectory(fileRoute.parent)
            Files.createFile(fileRoute)
        }

        val listUsers = mutableListOf<Map<String, List<String>>>()
        val users = mutableMapOf<String, MutableList<String>>()
        var titles = listOf<String>()
        var isfirstLine = true

        //Leer fichero
        val br: BufferedReader = Files.newBufferedReader(fileRoute)
        br.use {
            it.forEachLine { line ->
                if (isfirstLine) {
                    titles = line.split(";")
                    titles.forEach{ title ->
                        users[title] = mutableListOf()
                    }
                    isfirstLine = false
                } else {
                    val userData = line.split(";")
                    for (i in userData.indices) {
                        users[titles[i]]?.add(userData[i])
                        listUsers.add(users)
                    }
                }
            }
        }

        listUsers.forEach { user ->
            user.forEach { (_, datos) ->
                datos.sorted()
            }
        }

        return listUsers
    }

    fun addStudent(studentList: List<Map<String, List<String>>>) {
        TODO("Crear una columna NotaFinal (map)")
    }

    fun gradesManager(studentList: List<Map<String, Int>>): Pair<List<String>,List<String>> {
        TODO("Que devuelva dos listas y que una sea Aprobados y otra suspensos")
    }
}