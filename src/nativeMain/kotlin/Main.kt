import com.github.ajalt.clikt.core.subcommands
import commands.Init
import commands.Report
import commands.Scaffold
import model.DKAssist

fun main(args: Array<String>) = DKAssist().run {
    loadConfig()

    subcommands(
        Init(config),
        Scaffold(config),
        Report(config)
    )

    main(args)
}
