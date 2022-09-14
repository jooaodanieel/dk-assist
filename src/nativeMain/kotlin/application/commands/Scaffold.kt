package application.commands

import com.github.ajalt.clikt.core.CliktCommand
import core.model.Config
import core.model.FileSystem
import application.platform.FileSystemImpl
import core.services.ScaffoldRequiredFiles

class Scaffold(
    config: Config,
    fs: FileSystem = FileSystemImpl()
) : CliktCommand(help = "Generates all the requiredFiles") {

    private val scaffoldRequiredFiles = ScaffoldRequiredFiles(config, fs)

    override fun run() = scaffoldRequiredFiles.scaffold()
}
