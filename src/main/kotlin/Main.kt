package org.example

import java.io.File

fun main() {
    val manager = GestorNotas()
    val file = File("src/main/resources/calificaciones.csv")
    val students = manager.transform(file)

    manager.calculateFinalGrade(students)

    val (pass, fail) = manager.passOrFail(students)

    println("Alumnos aprobados:")
    pass.forEach { student ->
        println("Nombre: ${student["Nombre"]} ${student["Apellidos"]}, Nota Final: ${student["NotaFinal"]}")
    }

    println("\nAlumnos suspensos:")
    fail.forEach { student ->
        println("Nombre: ${student["Nombre"]} ${student["Apellidos"]}, Nota Final: ${student["NotaFinal"]}")
    }
}