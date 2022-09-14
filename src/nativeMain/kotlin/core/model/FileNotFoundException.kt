package core.model

class FileNotFoundException(fileName: String) : DKAssistException("file not found [$fileName]")
