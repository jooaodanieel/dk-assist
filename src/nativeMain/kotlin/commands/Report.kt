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

private typealias Analysis = Map<String, Status>
private typealias MutableAnalysis = MutableMap<String, Status>

class Report(
    private val config: Config,
    private val fs: FileSystem = FileSystemImpl()
) : CliktCommand(help = "Displays which of the requiredFiles need to be filled yet") {

    override fun run() = analyze().let { report(it) }

    private fun report(analysis: Analysis) {
        println("DKAssist Report")
        analysis.keys
            .groupBy { analysis[it]!! }
            .forEach { (status, files) ->
                val color = selectColor(status)

                val reset = "\u001b[0m"

                println("$color- $status $reset")
                files.forEach { println("$color    $it$reset") }
                println()
            }
    }

    private fun selectColor(status: Status) = when (status) {
        Status.NotCreated -> "\u001b[31m"
        Status.Empty -> "\u001b[33m"
        else -> "\u001b[32m"
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
