package core.model

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import application.platform.FileSystemImpl

interface ConfigLoader {
    var config: Config

    fun loadConfig()
}

class AssistfileLoader : ConfigLoader {

    companion object {
        const val CONFIG_FILE_NAME = "Assistfile.json"
    }

    override lateinit var config: Config

    override fun loadConfig() {
        config = try {
            FileSystemImpl()
                .readFile(CONFIG_FILE_NAME)
                .let { Json.decodeFromString(it) }
        } catch (e: Exception) {
            Config.empty()
        }
    }
}
