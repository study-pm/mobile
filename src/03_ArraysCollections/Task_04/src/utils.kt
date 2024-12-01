package utils
/**
 * Collection of console control characters representing ANSI color codes used in output
 */
enum class OutputType(val code: String) {
    Success("\u001b[32m"),
    Error("\u001b[31m"),
    Reset("\u001b[0m")
}

/**
 * Set color to console out considering the input type
 * @param msg input message string
 * @param type type of output that defines the output string color (`Success` | `Error`)
 * @return colorized string fragment ending with color reset code
 *
 * Usage examples:
 * ```kotlin
 * println(colorizedOut("\nSuccess", OutputType.Success))
 * println(colorizedOut("\nInvalid input", OutputType.Error))
 * ```
 */
fun colorizeOut(msg: String, type: OutputType) = type.code + msg + OutputType.Reset.code
