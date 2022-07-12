package model

class FileNotFoundException(fileName: String) : DKAssistException("file not found [$fileName]")
