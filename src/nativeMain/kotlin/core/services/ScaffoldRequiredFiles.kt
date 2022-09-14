package core.services

import core.model.Config
import core.model.EnvVar
import core.model.FileSystem

class ScaffoldRequiredFiles(
    private val config: Config,
    private val fs: FileSystem
) {
    fun scaffold() {
        config.requiredFiles
            .forEach { (path, desc) ->
                val isDotEnv = path.matches(".*env\$".toRegex())

                if (isDotEnv) generateDotFile(path, config.envSample)
                else generateRegularFile(path, desc)
            }
    }

    private fun generateDotFile(path: String, sample: Set<EnvVar>) {
        val text = sample.joinToString("\n\n") { (name, desc, default) ->
            var entry = ""

            if (desc != null) entry += "# $desc\n"

            entry += "$name="

            if (default != null) entry += default

            entry
        }
        fs.writeFile(path, text)
    }

    private fun generateRegularFile(path: String, description: String?) {
        if (fs.fileExists(path)) return

        val text = placeholder(description)
        fs.writeFile(path, text)
    }

    private fun placeholder(description: String?): String = """
        /**
          * ${ description ?: "" }
          * THIS FILE WAS AUTO GENERATED USING DK-ASSIST (https://github.com/jooaodanieel/dk-assist)
          */
    """.trimIndent()
}