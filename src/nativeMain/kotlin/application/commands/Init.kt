package application.commands

import com.github.ajalt.clikt.core.CliktCommand
import core.model.Config
import core.model.FileSystem
import application.platform.FileSystemImpl
import core.services.CreateAssistfile

class Init(
    config: Config,
    fs: FileSystem = FileSystemImpl()
) : CliktCommand(help = "Spawns an empty Assistfile.json for you") {

    private val createAssistfile: CreateAssistfile = CreateAssistfile(config, fs)

    override fun run() = createAssistfile.create()
}
