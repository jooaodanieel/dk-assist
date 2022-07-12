package commands

import com.github.ajalt.clikt.core.CliktCommand
import model.Config

class Scaffold(private val config: Config) : CliktCommand(help = "Generates all the requiredFiles") {
    override fun run() {
        println("scaffolding...")
        println(config)
    }
}
