package commands

import com.github.ajalt.clikt.core.CliktCommand
import model.Config

class Report(private val config: Config) : CliktCommand(help = "Displays which of the requiredFiles need to be filled yet") {
    override fun run() {
        println("reporting...")
        println(config)
    }
}
