package model

interface FileSystem {
    fun readFile(fileName: String): String

    fun writeFile(fileName: String, content: String)

    fun fileExists(filename: String): Boolean
}
