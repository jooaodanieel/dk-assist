package core.services

import core.model.AssistfileLoader
import core.model.Config
import core.model.FileSystem
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class CreateAssistfile(
    private val config: Config,
    private val fs: FileSystem
) {
    fun create() {
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