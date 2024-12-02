/**
 * Collection of utils helping add formatting to console output
 */
class Console {
    /**
     * Collection of control characters representing ANSI color codes used for console output
     */
    enum class Mode(val code: String) {
        Reset("\u001b[0m"),         // Default
        Error("\u001b[31m"),        // Red
        Success("\u001b[32m"),      // Green
        Warning("\u001b[33m"),      // Yellow
        Info("\u001b[34m"),         // Blue
    }

    /**
     * Collection of control characters representing ANSI codes used for additional styling
     */
    enum class Style(val code: String) {
        None(""),                   // No additional styling
        Strong("\u001b[1m"),        // Bold
        Emphasized("\u001b[3m"),    // Italic
        Important("\u001b[4m"),     // Underlined
        Deleted("\u001b[9m"),       // Strikethrough

    }
    companion object {
        /**
         * Sets additional formatting to console out based on the input semantics
         * @param msg an input message [String]
         * @param mode color [Mode] defining the output [String] color
         * @param style font [Style] defining the additional styling
         * @return formatted [String] fragment with trailing reset code
         *
         * Usage examples:
         * ```kotlin
         * println(Console.format("Success", Mode.Success, Style.Strong))
         * println(Console.format("Warning", Mode.Warning, Style.Important))
         * println(Console.format("Invalid input", Mode.Error))
         * ```
         */
        fun format(msg: String, mode: Mode, style: Style = Style.None) = mode.code + style.code + msg + Mode.Reset.code
    }
}
