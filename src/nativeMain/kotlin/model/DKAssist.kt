package model

import com.github.ajalt.clikt.core.CliktCommand

class DKAssist(
    private val configLoader: ConfigLoader = AssistfileLoader()
) : CliktCommand(), ConfigLoader by configLoader {
    override fun run() {}
}
