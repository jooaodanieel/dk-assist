import com.github.ajalt.clikt.core.subcommands
import commands.Report
import commands.Scaffold
import model.DKAssist
import model.DKAssistException

fun main(args: Array<String>) {
    try {
        DKAssist().let {
            it.subcommands(
                Scaffold(it.config),
                Report(it.config)
            )

            it.main(args)
        }
    } catch (dkae: DKAssistException) {
        println(dkae.message)
    }
}
