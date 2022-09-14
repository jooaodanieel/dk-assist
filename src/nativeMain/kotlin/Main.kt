import com.github.ajalt.clikt.core.subcommands
import application.commands.Init
import application.commands.Report
import application.commands.Scaffold
import core.model.DKAssist

fun main(args: Array<String>) = DKAssist().run {
    loadConfig()

    subcommands(
        Init(config),
        Scaffold(config),
        Report(config)
    )

    main(args)
}
