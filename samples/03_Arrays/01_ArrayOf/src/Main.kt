fun main() {
    val arrNum: Array<Int>
    arrNum = arrayOf(12, 25, 37, 84, 95)    // преобразует перечисляемые значения в целочисленный массив

    val arrTest = arrayOf(4,7, 9.65, 7.82)  // получаем массив указанного типа
    val arrD: Array<Double> = arrayOf(65.98, 32.81, 83.96, 73.65)

    val testN = arrNum[1]                   // присваиваем значение
    val testU = arrNum.get(4)               // элементу массива

    arrNum[0] = 56                          // получаем значение
    arrNum.set(3, 97)                       // элемента массива
}
