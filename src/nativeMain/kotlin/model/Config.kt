package model

import kotlinx.serialization.Serializable

@Serializable
data class RequiredFile(
    val path: String,
    val description: String? = null
)

@Serializable
data class EnvVar(
    val name: String,
    val description: String? = null,
    val default: String? = null
)

@Serializable
data class Config(
    val requiredFiles: Set<RequiredFile>,
    val envSample: Set<EnvVar>
)
