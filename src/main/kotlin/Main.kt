package org.example

import java.io.File
import java.nio.file.Path

fun main() {
    val root = Path.of("src")
    val fileRoute = root.resolve("main").resolve("resources").resolve("calificaciones.csv")
    val file = File(fileRoute.toUri())
    val gestorNotas = GestorNotas(fileRoute)
    gestorNotas.transform(file)
}