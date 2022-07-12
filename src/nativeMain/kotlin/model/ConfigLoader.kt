package model

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import platform.FileSystemImpl

interface ConfigLoader {
    var config: Config
}

class AssistfileLoader : ConfigLoader {

    companion object {
        const val CONFIG_FILE_NAME = "Assistfile.json"
    }

    override lateinit var config: Config

    init {
        loadConfig()
    }

    private fun loadConfig() {
        config = FileSystemImpl()
            .readFile(CONFIG_FILE_NAME)
            .let { Json.decodeFromString(it) }
    }
}
