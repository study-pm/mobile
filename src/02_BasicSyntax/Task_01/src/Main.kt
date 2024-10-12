fun main() {
    print("Enter character sequence: ")
    val input = readln()

    if (input.isEmpty()) {
        println("The input is empty")
        return
    }

    var res = ""

    var count = 1
    var lastChar: Char? = null

    for (c in input) {
        if (c == lastChar) count++
        else {
            res += if (count != 1) count else ""
            res += c
            lastChar = c
            count = 1
        }
    }
    if (count > 1) res += count

    println(res)
}

// Sample string:   AAADSSSRRTTHAAAA
// Expected output: A3DS3R2T2HA4
