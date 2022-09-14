package application.platform

import core.model.FileNotFoundException
import core.model.FileSystem
import okio.Path.Companion.toPath
import okio.buffer
import okio.use
import platform.posix.system

class FileSystemImpl : FileSystem {
    override fun fileExists(filename: String): Boolean {
        val filePath = filename.toPath()
        return okio.FileSystem.SYSTEM.exists(filePath)
    }

    override fun readFile(fileName: String): String {
        val filePath = fileName.toPath()

        try {
            var content = ""
            okio.FileSystem.SYSTEM.source(filePath).use { fs ->
                fs.buffer().use { buff ->
                    while (true) {
                        val line = buff.readUtf8Line() ?: break
                        content += line + "\n"
                    }
                }
            }

            return content
        } catch (_: okio.FileNotFoundException) {
            throw FileNotFoundException(fileName)
        }
    }

    override fun writeFile(fileName: String, content: String) {
        val filePath = fileName.toPath()

        system("mkdir -p ${filePath.parent}")

        okio.FileSystem.SYSTEM
            .sink(filePath)
            .buffer()
            .use { it.writeUtf8(content) }

        val color = "\u001b[33m"
        val reset = "\u001b[0m"
        println("${color}Created $fileName$reset")
    }
}
