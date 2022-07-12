package commands

import com.github.ajalt.clikt.core.CliktCommand
import model.Config
import model.DKAssistException

class Scaffold(@Suppress("unused") private val config: Config) : CliktCommand(help = "Generates all the requiredFiles") {
    override fun run() {
        throw DKAssistException("scaffold not implemented yet")
    }
}
