/**
 * Provides encryption/decryption methods
 */
interface Cryptable {
    fun encrypt(msg: String): String
    fun decrypt(msg: String): String
}
