fun main() {
    println("ParentClass(25, 35)===========================")
    val one = ParentClass(25, 35)
    println("\nParentClass(65, 75, 50)=====================")
    val two = ParentClass(65, 75, 50)
    println("\nDescendantClassOne(5, 15)===================")
    val three = DescendantClassOne(5, 15)
    println("\nDescendantClassOne(5, 25, 15, 47)===========")
    val four = DescendantClassOne(5, 25, 15, 47)
    println("\nDescendantClassTwo(5, 9, 6)=================")
    val five = DescendantClassTwo(5, 9, 6)
    println("\nDescendantClassThree(9, 7, 6)===============")
    val six = DescendantClassThree(9, 7, 6)
    println("\nDescendantClassFour(18, 78)=================")
    val seven = DescendantClassFour(18, 78)
    println("\nDescendantClassFour(18, 78, 75)=============")
    val eight = DescendantClassFour(18, 78, 75)
    println("\nDescendantClassFour(15, 78, 96, 34)=========")
    val nine = DescendantClassFour(15, 78, 96, 34)
    println("\nDescendantClassFour(46, 78, \"99\")=========")
    val ten = DescendantClassFour(15, 78, "99")
}

open class ParentClass(a: Int, b: Int) {
    init {
        println("init-блок от ParentClass")
    }

    constructor(a: Int, b: Int, c: Int) : this(a, b) {
        println("Вторичный конструктор от ParentClass")
    }
}

class DescendantClassOne(a: Int, b: Int) : ParentClass(a, b) {
    init {
        println("init-блок от DescendantClassOne")
    }

    // constructor(a: Int, b: Int, c: Int):super(a,b)
    constructor(a: Int, b: Int, c: Int, d: Int) : this(a, b) {
        println("Вторичный конструктор от DescendantClassOne")
    }
}

class DescendantClassTwo(a: Int, b: Int, c: Int) : ParentClass(a, b) {
    init {
        println("init-блок от DescendantClassTwo")
    }
    // constructor(a: Int, b: Int, c: Int):super(a, b)
}

class DescendantClassThree(a: Int, b: Int, c: Int) : ParentClass(a, b, c) {
    init {
        println("init-блок от DescendantClassThree")
    }
    // constructor(a: Int, b: Int, c: Int) : super(a, b, c)
}

class DescendantClassFour : ParentClass {
    init {
        println("init-блок от DescendantClassFour")
    }

    constructor(a: Int, b: Int) : super(a, b) {
        println("Вторичный конструктор от DescendantClassFour constructor(a: Int, b: Int) : super(a, b)")
    }

    constructor(a: Int, b: Int, c: Int) : super(a, b) {
        println("Вторичный конструктор от DescendantClassFour constructor(a: Int, b: Int, c: Int) : super(a, b)")
    }

    constructor(a: Int, b: Int, c: Int, d: Int) : super(a, b, c) {
        println("Вторичный конструктор от DescendantClassFour constructor(a: Int, b: Int, c: Int, d: Int) : super(a, b, c)")
    }

    constructor(a: Int, b: Int, c: String) : super(a, b, c.toInt()) {
        println("Вторичный конструктор от DescendantClassFour constructor(a: Int, b: Int, c: String) : super(a, b, c)")
    }
}
