/**
 * Serves as a basic class for implementing various ciphers in derived classes and provides encryption related utils
 */
abstract class Crypto : Cryptable {
    /**
     * Collection of useful constants for different language characters
     * @property num numeric symbols (digits)
     * @property letEnLow English alphabet letters lowercase
     * @property letEn English alphabet letters
     * @property letRuLow Russian alphabet letters lowercase
     * @property letRuLowClip Russian alphabet letters lowercase except for 'ё` and 'й' chars
     * @property letRu Russian alphabet letters
     * @property letCyr Cyrillic / Slavonic / Slavic
     * @property baseLat Basic Latin
     * @property baseLatLow Basic Latin lowercase
     * @property extLatA Latin Extended A
     * @property extLatB Latin Extended B
     */
    companion object Chars {
        val num = '0'..'9'
        val letEnLow = 'a'..'z'
        val letEn = 'A'..'z'
        val letRuLow = ('а'..'е') + 'ё' + ('ж'..'я')
        val letRuLowClip = ('а'..'и') + ('к'..'я')
        val letRu = ('А'..'Е') + 'Ё' + ('Ж'..'е') + 'ё' + ('ж'..'я')
        val letCyr = 'Ѐ'..'ӿ'
        val baseLat = ' '..'~'
        val baseLatLow = (' '..'@') + ('['..'~')
        val extLatA = 'Ā'..'ſ'
        val extLatB = 'ƀ'..'ɏ'

        /**
         * Represent character frequency analysis as probabilities for each symbol for several common european languages
         * Source: https://beobaxter.livejournal.com/892772.html
         */
        enum class FqMap(val value: Map<Char, Double>) {
            De(mapOf(
                'e' to .1918, 'n' to .1020, 'i' to .0821, 's' to .0707, 'r' to .0701, 't' to .0586, 'a' to .0552,
                'h' to .0502, 'd' to .0491, 'u' to .0422, 'g' to .0360, 'l' to .0348, 'c' to .0294, 'o' to .0214,
                'f' to .0196, 'm' to .0169, 'b' to .0156, 'w' to .0138, 'k' to .0133, 'z' to .0117, 'v' to .0084,
                'p' to .0054, 'j' to .0016, 'x' to .0001, 'x' to .0001, 'y' to .0001
            )),
            En(mapOf(
                'e' to .1286, 't' to .0972, 'a' to .0796, 'i' to .0777, 'n' to .0751, 'r' to .0683, 'o' to .0662,
                's' to .0662, 'h' to .0539, 'd' to .0401, 'l' to .0351, 'c' to .0284, 'f' to .0262, 'u' to .0248,
                'm' to .0243, 'g' to .0199, 'p' to .0181, 'w' to .0180, 'b' to .0160, 'y' to .0152, 'v' to .0115,
                'k' to .0041, 'q' to .0017, 'x' to .0017, 'j' to .0016, 'z' to .0005
            )),
            Es(mapOf(
                'e' to .1415, 'a' to .0129, 'o' to .0884, 's' to .0764, 'i' to .0701, 'r' to .0695, 'n' to .0620,
                'l' to .0552, 'd' to .0467, 'c' to .0442, 't' to .0436, 'u' to .0400, 'p' to .0326, 'm' to .0255,
                'q' to .0155, 'y' to .0105, 'b' to .0103, 'g' to .0100, 'h' to .0091, 'f' to .0070, 'v' to .0067,
                'z' to .0031, 'j' to .0024, 'x' to .0007, 'k' to .0001, 'w' to .0001
            )),
            Fr(mapOf(
                'e' to .1776, 's' to .0823, 'a' to .0768, 'n' to .0761, 't' to .0730, 'i' to .0723, 'r' to .0681,
                'u' to .0605, 'l' to .0589, 'o' to .0534, 'd' to .0360, 'c' to .0332, 'p' to .0324, 'm' to .0272,
                'q' to .0134, 'v' to .0127, 'g' to .0110, 'f' to .0106, 'b' to .0080, 'h' to .0064, 'x' to .0054,
                'y' to .0021, 'j' to .0019, 'z' to .0007, 'k' to .0001, 'w' to .0001
            )),
            It(mapOf(
                'i' to .1204, 'e' to .1163, 'a' to .1112, 'o' to .0892, 'n' to .0768, 't' to .0707, 'r' to .0656,
                'l' to .0595, 's' to .0481, 'c' to .0411, 'd' to .0354, 'u' to .0309, 'p' to .0266, 'm' to .0265,
                'g' to .0173, 'v' to .0167, 'z' to .0124, 'f' to .0115, 'b' to .0107, 'h' to .0083, 'q' to .0048,
                'j' to .0001, 'k' to .0001, 'w' to .0001, 'x' to .0001, 'y' to .0001
            )),
            Ru(mapOf(
                'о' to .0764, 'е' to .0732, 'а' to .0629, 'и' to .0577, 'т' to .0549, 'н' to .0490, 'р' to .0459,
                'с' to .0404, 'в' to .0355, 'п' to .0330, 'к' to .0302, 'л' to .0299, 'м' to .0275, 'д' to .0265,
                'у' to .0222, 'я' to .0153, 'ы' to .0143, 'ь' to .0138, 'з' to .0133, 'й' to .0125, 'б' to .0114,
                'ч' to .0094, 'г' to .0083, 'ю' to .0081, 'ж' to .0079, 'х' to .0048, 'щ' to .0042, 'ф' to .0036,
                'ш' to .0026, 'э' to .0023, 'ц' to .0021, 'ъ' to .0003,
                ' ' to .2005,
            )),
            RuSt(mapOf(
                'а' to .069, 'б' to .013, 'в' to .038, 'г' to .014, 'д' to .024, 'е' to .071, 'ж' to .007, 'з' to .016,
                'и' to .064, 'й' to .010, 'к' to .029, 'л' to .039, 'м' to .027, 'н' to .057, 'о' to .094, 'п' to .026,
                'р' to .042, 'с' to .046, 'т' to .054, 'у' to .023, 'ф' to .003, 'х' to .008, 'ц' to .005, 'ч' to .012,
                'ш' to .006, 'щ' to .004, 'ъ' to .001, 'ц' to .015, 'ь' to .013, 'э' to .002, 'ю' to .005, 'я' to .017,
                'ы' to .015,
                ' ' to .136,
            ))
        }
    }

    /**
     * Stores a text fragment and provides its character analysis
     * @param value a text [String]
     */
    data class Text(var value: String) {
        val hasEn: Boolean
            get() = Regex("[A-z]").containsMatchIn(value)
        val hasRu: Boolean
            get() = Regex("[А-я]|Ёё").containsMatchIn(value)
        val hasMix: Boolean
            get() = hasEn && hasRu
        val isEmpty: Boolean
            get() = value.isEmpty()
        val isNum: Boolean
            get() = value.all { it.isDigit() }
        val isEnLow: Boolean
            get() = value.matches(Regex("^[a-z]+$"))
        val isEnLet: Boolean
            get() = value.matches(Regex("^[A-Za-z]+$"))
        val isEnNum: Boolean
            get() = value.matches(Regex("^[A-Za-z|\\d]+$"))
        val isEnBase: Boolean
            get() = value.matches(Regex("^[A-Za-z|\\d\\s\\p{Punct}]+$"))
        val isRuLow: Boolean
            get() = value.matches(Regex("^[а-я|ё]+$"))
        val isRuLet
            get() = value.matches(Regex("^[А-я|Ёё]+$"))
        val isRuNum: Boolean
            get() = value.matches(Regex("^[А-я|Ёё\\d]+$"))
        val isRuBase: Boolean
            get() = value.matches(Regex("^[А-я|Ёё\\d\\s\\p{Punct}]+$"))
        val isBaseLat: Boolean
            get() = value.matches(Regex("^[\\u0020-\\u007E]$"))
        val isBaseLatLow: Boolean
            get() = value.matches(Regex("^\\p{ASCII}\\p{Lower}+$"))
        val isMixLow: Boolean
            get() = value.matches(Regex("^[a-zа-я|ё]+$"))
        val isMixLet: Boolean
            get() = value.matches(Regex("^[A-Za-z\\p{IsCyrillic}]+$"))
        val isLatLet: Boolean
            get() = value.matches(Regex("^\\p{IsLatin}+$"))
        val isLatA: Boolean
            get() = value.matches(Regex("^[\\u0100-\\u017F]+$"))
        val isLatB: Boolean
            get() = value.matches(Regex("^[\\u0180-\\u024F]+$"))
        val isLatIPA: Boolean
            get() = value.matches(Regex("^[\\u0250-\\u02AD]+$"))
        val isCyr: Boolean
            get() = value.matches(Regex("^\\p{IsCyrillic}"))
        val isGreek: Boolean
            get() = value.matches(Regex("^\\p{IsGreek}"))

        /**
         * Match character set based on the stored text character analysis
         * @return character set as [Iterable] of [Char] values
         */
        fun matchCharSet() = when {
            isEmpty     -> baseLat + letRu
            isNum       -> num
            isEnLow     -> letEnLow
            isEnLet     -> letEn
            isEnNum     -> letEn + num
            isEnBase    -> baseLat
            isRuLow     -> letRuLow
            isRuLet     -> letRu
            isRuNum     -> letRu + num
            isRuBase    -> letRu + baseLat - letEn
            isMixLow    -> letRuLow + letEnLow
            isMixLet    -> letRu + letEn
            else        -> value.toSet().toList() // Ensures no duplicating values
        }

        /**
         * Match letter frequency map based on the stored text character analysis
         * @return frequency map object as [FqMap]
         */
        fun matchFqMap(sample: String) = if (Text(sample).isEnLet) Chars.FqMap.En else Chars.FqMap.Ru

        /**
         * Gets all data object properties (incl. getters)
         * @return [String] value of object props
         */
        override fun toString(): String {
            return "value=\"$value\", hasEn=$hasEn, hasRu=$hasRu, hasMix=$hasMix, isNum=$isNum,\n" +
                    "isEnLow=$isEnLow, isEnLet=$isEnLet, isEnNum=$isEnNum, isEnBase=$isEnBase,\n" +
                    "isRuLow=$isRuLow, isRuLet=$isRuLet, isRuNum=$isRuNum, isRuBase=$isRuBase,\n" +
                    "isBaseLat=$isBaseLat, isBaseLatLow=$isBaseLatLow, isMixLow=$isMixLow, isMixLet=$isMixLet,\n" +
                    "isLatLet=$isLatLet, isLatA=$isLatA, isLatB=$isLatB, isLatIPA=$isLatIPA\n" +
                    "isCyr=$isCyr, isGreek=$isGreek"
        }
    }

    abstract override fun encrypt(msg: String): String
    abstract override fun decrypt(msg: String): String
    abstract fun printCipherTable()
    abstract fun printResult(plain: String, cipher: String, decrypted: String)
}
