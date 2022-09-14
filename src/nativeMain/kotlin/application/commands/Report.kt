package application.commands

import com.github.ajalt.clikt.core.CliktCommand
import core.model.Config
import core.model.FileSystem
import application.platform.FileSystemImpl
import core.services.ReportConfigurationStatus

class Report(
    config: Config,
    fs: FileSystem = FileSystemImpl()
) : CliktCommand(help = "Displays which of the requiredFiles need to be filled yet") {

    private val reportConfigurationStatus = ReportConfigurationStatus(config, fs)

    override fun run() = reportConfigurationStatus.reportStatus()
}
