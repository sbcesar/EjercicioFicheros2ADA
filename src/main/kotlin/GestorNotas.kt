package org.example

import java.io.BufferedReader
import java.io.File
import java.nio.file.Files

class GestorNotas() {

    fun transform(file: File): List<MutableMap<String, String>> {

        val listUsers = mutableListOf<MutableMap<String, String>>()

        //Leer fichero
        val br: BufferedReader = Files.newBufferedReader(file.toPath())

        br.use {

            //Ignora la primera linea
            val firstLine = br.readLine().split(";")

            it.forEachLine { line ->
                val userData = line.split(";")
                val users = mutableMapOf<String, String>()

                for (i in userData.indices) {
                    users[firstLine[i]] = userData[i]
                }

                listUsers.add(users)
            }
        }

        return listUsers.sortedBy { it["Apellidos"] }
    }

    fun calculateFinalGrade(students: List<MutableMap<String, String>>) {

        students.forEach { student ->
            val firstParcialGrade = calculateExamGrade(student["Parcial1"], student["Ordinario1"])
            val secondParcialGrade = calculateExamGrade(student["Parcial2"], student["Ordinario2"])
            val practiceGrade = calculateExamGrade(student["Practicas"], student["OrdinarioPracticas"])

            val finalGrade = (firstParcialGrade * 0.30) + (secondParcialGrade * 0.30) + (practiceGrade * 0.40)

            student["NotaFinal"] = String.format("%.2f", finalGrade)
        }
    }

    private fun calculateExamGrade(grade: String?, ordinary: String?): Double {

        val examGrade = grade?.replace(",",".")?.toDoubleOrNull() ?: 0.0
        val ordinaryGrade = ordinary?.replace(",", ".")?.toDoubleOrNull() ?: 0.0

        return if (examGrade >= 5) examGrade else ordinaryGrade
    }

    fun passOrFail(students: List<MutableMap<String, String>>): Pair<List<MutableMap<String, String>>, List<MutableMap<String, String>>> {
        val pass = mutableListOf<MutableMap<String, String>>()
        val fail = mutableListOf<MutableMap<String, String>>()

        students.forEach { student ->
            if (isApproved(student)) {
                pass.add(student)
            } else {
                fail.add(student)
            }
        }

        return Pair(pass,fail)
    }

    // Función que verifica si la asistencia es suficiente
    private fun sufficientAssistance(assistance: String?): Boolean {
        val asistenciaPorcentaje = assistance?.replace("%", "")?.toDoubleOrNull() ?: 0.0
        return asistenciaPorcentaje >= 75.0
    }

    // Función que verifica si la nota es suficiente (mayor o igual a 4)
    private fun sufficientGrade(grade: String?): Boolean {
        val convertedGrade = grade?.replace(",", ".")?.toDoubleOrNull() ?: 0.0
        return convertedGrade >= 4.0
    }

    // Función que verifica si la nota final es suficiente (mayor o igual a 5)
    private fun sufficientFinalNote(finalGrade: String?): Boolean {
        val convertedGrade = finalGrade?.replace(",", ".")?.toDoubleOrNull() ?: 0.0
        return convertedGrade >= 5.0
    }

    private fun isApproved(student: MutableMap<String, String>): Boolean {
        val assistance = sufficientAssistance(student["Asistencia"])
        val firstParcialGrade = sufficientGrade(student["Parcial1"])
        val secondParcialGrade = sufficientGrade(student["Parcial2"])
        val practiceGrade = sufficientGrade(student["Practicas"])
        val finalGrade = sufficientFinalNote(student["NotaFinal"])

        return assistance && firstParcialGrade && secondParcialGrade && practiceGrade && finalGrade
    }
}