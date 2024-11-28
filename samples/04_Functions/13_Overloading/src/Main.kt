fun main() {
    println("7 < 9 < 16 => " + ascSequence(7, 9, 16))
    println("7.8 < 7.81 < 16.85 => " + ascSequence(7.8, 7.81, 16.85))
    println("7.8f < 7.81f < 16.85f => " + ascSequence(7.8f, 7.81f, 16.85f))
    println("87 < 69.3F < 56.15 => " + ascSequence(87, 69.3F, 56.15))
}

fun ascSequence(a: Int, b: Int, c: Int) = b in (a + 1)..c

fun ascSequence(a: Double, b: Double, c: Double) = a < b && b < c

fun ascSequence(a: Float, b: Float, c: Float) = a < b && b < c

fun ascSequence(a: Int, b: Float, c: Double) = a < b && b < c
