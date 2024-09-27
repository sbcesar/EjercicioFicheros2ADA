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
        val user = mutableMapOf<String, MutableList<String>>()
        var titles = listOf<String>()
        var isfirstLine = true

        //Leer fichero
        val br: BufferedReader = Files.newBufferedReader(fileRoute)
        br.use {
            it.forEachLine { line ->
                if (isfirstLine) {
                    titles = line.split(";")
                    titles.forEach{ title ->
                        user[title] = mutableListOf()
                    }
                    isfirstLine = false
                } else {
                    val userData = line.split(";")
                    for (i in userData.indices) {
                        user[titles[i]]?.add(userData[i])
                    }
                }
            }
        }
        listUsers.add(user)
        listUsers.forEach { user ->
            user.filter { (key,values) ->

            }
        } }
        return
    }

    fun addStudent(studentList: List<Map<String, Int>>) {

    }

    fun gradesManager(studentList: List<Map<String, Int>>): Pair<List<String>,List<String>> {

    }
}