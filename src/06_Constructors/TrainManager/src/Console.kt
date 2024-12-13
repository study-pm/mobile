/**
 * Represents utils helping add formatting to console output
 */
class Console {
    /**
     * Collection of control characters representing ANSI color codes used for console output
     */
    enum class Color(val code: String) {
        Reset("\u001b[0m"),
        Red("\u001b[31m"),
        Green("\u001b[32m"),
        Yellow("\u001b[33m"),
        Blue("\u001b[34m"),
        Magenta("\u001b[35m"),
        Cyan("\u001b[36m"),
        White("\u001b[37m"),
        Gray("\u001b[90m"),
    }

    /**
     * Collection of control characters representing ANSI codes used for additional styling
     */
    enum class Style(val code: String) {
        None(""),
        Bold("\u001b[1m"),
        Italic("\u001b[3m"),
        Underlined("\u001b[4m"),
        Strikethrough("\u001b[9m"),
    }

    /**
     * Contains supplementary data related to [Console]
     */
    companion object {
        /**
         * Sets additional formatting to console out based on the input semantics
         * @param msg an input message [String]
         * @param color a [Color] defining the output [String] color
         * @param styles font list of [Style] enums defining the additional styling
         * @return formatted [String] fragment with trailing reset code
         *
         * Usage examples:
         * ```kotlin
         * println(Console.format("Success", Mode.Success, Style.Strong))
         * println(Console.format("Warning", Mode.Warning, Style.Important))
         * println(Console.format("Invalid input", Mode.Error))
         * println(Console.format("some msg", styles = setOf(Console.Style.Strong, Console.Style.Important)))
         * println(Console.format("information", Console.Mode.Info, Console.Style.Emphasized, Console.Style.Important)))
         * ```
         */
        fun format(msg: String, color: Color = Color.Reset, styles: Set<Style>)
                = color.code + styles.fold("") { acc, style -> acc + style.code } + msg + Color.Reset.code
        fun format(msg: String, color: Color = Color.Reset, vararg styles: Style) = format(msg, color, styles.toSet())
        fun format(msg: String, vararg styles: Style) = format(msg, Color.Reset, styles.toSet())
    }
}
