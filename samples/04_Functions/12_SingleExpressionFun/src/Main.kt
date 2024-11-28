import java.lang.Math.pow
import kotlin.math.pow

fun main() {
    val testBasis = 7.0
    val testDegree = 4.0
    println(nthDegreeRoot(testBasis, testDegree))
    println(nthDegreeRootKotlin(testBasis, testDegree))
}

fun nthDegreeRoot(basis: Double, degree: Double) = pow(pow(basis, degree), 1 /2.0)

fun nthDegreeRootKotlin(basis: Double, degree: Double) = basis.pow(degree).pow(1 / 2.0)
