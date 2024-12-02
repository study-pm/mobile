/* Камень-Ножницы-Бумага (три события)
 * Создать консольную игру "Камень-Ножницы-Бумага" (три события).
 * На консоль выводится выбор компьютера (случайное значение) и выбор игрока.
 * Для ускорения ввода выбора игрока можно условиться, что, например, 1 - это камень, 2 - ножницы, 3 - бумага.
 * Выполненное задание представить в виде ссылки на GitHub
 *
 * Победитель определяется по следующим правилам:
 * - Бумага побеждает камень («бумага обёртывает камень»).
 * - Камень побеждает ножницы («камень затупляет или ломает ножницы»).
 * - Ножницы побеждают бумагу («ножницы разрезают бумагу»).
 *
 * Если игроки показали одинаковый знак, то засчитывается ничья и игра переигрывается.
 */

/**
 * Defines three possible choices: `Rock`, `Scissors`, `Paper`
 */
enum class RockPaperScissors(val value: Int) {
    Rock(1),
    Scissors(2),
    Paper(3)
}

/**
 * Defines three possible game results: Tie, Computer wins, User wins
 */
enum class GameResult(val value: Int) {
    Tie(0),
    Computer(1),
    User(2)
}

/**
 * Applies Rock-Scissors-Paper game rules to the result
 * @param choices a [List] of players' [RockPaperScissors] choices
 * @return a [String] message describing what exactly happened
 */
fun analyzeResult(choices: List<RockPaperScissors>) = when {
    choices.all { it == choices[0] } -> "Equal items make tie."
    choices.contains(RockPaperScissors.Paper) && choices.contains(RockPaperScissors.Rock) -> "Paper wraps Rock."
    choices.contains(RockPaperScissors.Rock) && choices.contains(RockPaperScissors.Scissors) -> "Rock blunts Scissors."
    else -> "Scissors cut Paper."
}

/**
 * Generates computer choice
 * @return one of three possible [RockPaperScissors] choices selected by random
 */
fun getComputerChoice(): RockPaperScissors {
    val choice = (1..3).random()
    return RockPaperScissors.entries.find { it.value == choice }!!
}

/**
 * Gets score based on players' choices
 * @param user a [RockPaperScissors] choice of a user
 * @param comp a [RockPaperScissors] choice of a computer
 * @return a [GameResult] representation of the outcome
 */
fun getScore(user: RockPaperScissors, comp: RockPaperScissors) = when {
        user == comp    -> GameResult.Tie
        comp == RockPaperScissors.Rock && user == RockPaperScissors.Scissors ||
        comp == RockPaperScissors.Scissors && user == RockPaperScissors.Paper ||
        comp == RockPaperScissors.Paper && user == RockPaperScissors.Rock
                        -> GameResult.Computer
        else            -> GameResult.User
}

/**
 * Parses an input string to one of [RockPaperScissors] values
 * @param s an input [String]
 * @return a [RockPaperScissors] interpretation of the [s]
 * @throws Exception if the provided input does not correspond to any of the [RockPaperScissors] possible values
 */
fun parseInput(s: String): RockPaperScissors {
    if (s.isEmpty()) throw Exception("Must not be empty")
    var res = RockPaperScissors.entries.find { it.name == s[0].uppercase() + s.substring(1).lowercase() }
    if (res == null) res = RockPaperScissors.entries.find { it.value == s.toInt() }
    if (res == null) throw Exception("Invalid choice")
    return res
}

/**
 * Describes the game outcome based on the [GameResult] value
 * @param result a [GameResult] value
 * @return a [String] message representation of the [GameResult] value
 */
fun printResult(result: GameResult) = when(result.name) {
    "Computer"  -> "Sorry. Computer wins."
    "User"      -> "Congratulations! You win."
    else        -> "Tie. Let's play another round..."
}

fun main() {
    try {
        var compChoice: RockPaperScissors
        var userChoice: RockPaperScissors
        var score: GameResult
        do {
            // Inputs
            print("Enter your choice - may be either a number or a word (1 - Rock, 2 - Scissors, 3 - Paper): ")
            userChoice = parseInput(readln())
            println(Console.format("You chose:\t\t", Console.Mode.Success) + userChoice)
            compChoice = getComputerChoice()
            println(Console.format("Computer chose: ", Console.Mode.Info) + compChoice)

            // Get score
            score = getScore(userChoice, compChoice)

            // Resulting output
            println(analyzeResult(listOf(userChoice, compChoice)))
            // Define result output color mode
            val resMode = when(score.name) {
                "Computer"  -> Console.Mode.Info
                "User"      -> Console.Mode.Success
                else        -> Console.Mode.Warning
            }
            println(Console.format(printResult(score), resMode, Console.Style.Strong))
        } while (score == GameResult.Tie)
    } catch (exc: Exception) {
        println(Console.format("Invalid input: ", Console.Mode.Error) + exc.message)
    }
}

/* Sample I/O
=== Input 1 ===
1
=== Output 1 ===
You chose:		Rock
Computer chose: Scissors
Rock blunts Scissors.
Congratulations! You win.

=== Input 2 ===
scissors
=== Output 2 ===
You chose:		Scissors
Computer chose: Rock
Rock blunts Scissors.
Sorry. Computer wins.

=== Input 3 ===
PAPER
You chose:		Paper
Computer chose: Rock
Paper wraps Rock.
Congratulations! You win.

=== Input 4 ===
3
=== Output 4 ===
You chose:		Paper
Computer chose: Paper
Equal items make tie.
Tie. Let's play another round...
Enter your choice - may be either a number or a word (1 - Rock, 2 - Scissors, 3 - Paper): 2
You chose:		Scissors
Computer chose: Paper
Scissors cut Paper.
Congratulations! You win.

=== Input 5 ===

=== Output 5 ===
Invalid input: Must not be empty

=== Input 6 ===
stone
=== Output 6 ===
Invalid input: For input string: "stone"

=== Input 7 ===
5
=== Output 7 ===
Invalid input: Invalid choice
 */
