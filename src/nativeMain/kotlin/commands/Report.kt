package commands

import com.github.ajalt.clikt.core.CliktCommand
import model.Config
import model.FileNotFoundException
import model.FileSystem
import platform.FileSystemImpl

enum class Status(val printableName: String) {
    NotCreated("not created"),
    Empty("created, but somewhat empty"),
    Done("all done");

    override fun toString(): String = printableName
}

typealias Analysis = Map<String, Status>
typealias MutableAnalysis = MutableMap<String, Status>

class Report(
    private val config: Config,
    private val fs: FileSystem = FileSystemImpl()
) : CliktCommand(help = "Displays which of the requiredFiles need to be filled yet") {
    override fun run() = analyze().let { report(it) }

    private fun report(analysis: Analysis) {
        analysis.keys
            .groupBy { analysis[it]!! }
            .forEach { (status, files) ->
                println("$status")
                files.forEach { println("    - $it") }
                println()
            }
    }

    private fun analyze(): Analysis {
        return config.requiredFiles.fold(mutableMapOf()) { analysis: MutableAnalysis, (path, _) ->
            analysis[path] = try {
                val content = fs.readFile(path)
                handleContent(path, content)
            } catch (_: FileNotFoundException) {
                Status.NotCreated
            }

            analysis
        }
    }

    private fun handleContent(fileName: String, content: String): Status {
        if (fileName.matches("\\.env$".toRegex())) return handleDotEnvContent(content)

        return if (content.isEmpty()) Status.Empty else Status.Done
    }

    private fun handleDotEnvContent(content: String): Status {
        val anyUnset = content
            .split("\n")
            .any { it.matches("=\\w*$".toRegex()) }

        return if (anyUnset) Status.Empty else Status.Done
    }
}
