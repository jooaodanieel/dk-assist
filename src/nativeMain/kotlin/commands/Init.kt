package commands

import com.github.ajalt.clikt.core.CliktCommand
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import model.AssistfileLoader
import model.Config
import model.FileSystem
import platform.FileSystemImpl

class Init(
    private val config: Config,
    private val fs: FileSystem = FileSystemImpl()
) : CliktCommand(help = "Spawns an empty Assistfile.json for you") {

    override fun run() {
        if (config.isNotEmpty()) return

        Config.empty().let {
            val text = prettyStringify(it)
            fs.writeFile(AssistfileLoader.CONFIG_FILE_NAME, text)
        }
    }

    private fun prettyStringify(empty: Config): String {
        val prettyJson = Json { prettyPrint = true }
        return prettyJson.encodeToString(empty)
    }
}
