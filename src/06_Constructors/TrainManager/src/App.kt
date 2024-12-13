/**
 * Represents the application for handling Train Manager
 * @param greeting a message [String] to meet and greet a user at the application startup
 * @param stopWord a message [String] to exit the application
 * @property bookingOffice a [Booking] object representing a booking office instance
 * @property stopWord a lowercased version of the stopWord [String] param
 */
class App(private val greeting: String, stopWord: String = "out") {
    val bookingOffice = Booking()
    val stopWord = stopWord.lowercase()

    /**
     * Reads user input to confirm the application run and returns the user choice as a [Boolean]
     * @return `true` if confirmed, `false` if the stop word entered
     */
    fun confirm(): Boolean {
        print("So, let's get started! ")
        print(Console.format("Press Enter to continue (print \"$stopWord\" " +
                "before pressing the Enter key if you want to stop right now): ", styles = setOf(Console.Style.Bold)))

        return readln().lowercase() != stopWord
    }

    /**
     * Presents an application to users showing them a richly decorated program name
     */
    fun greet() {
        println(Console.format("*".repeat(greeting.length + 20), Console.Color.Magenta, Console.Style.Bold))
        println(Console.format(
            " ".repeat(10) + greeting + " ".repeat(10), Console.Color.Magenta, Console.Style.Bold)
        )
        println(Console.format("*".repeat(greeting.length + 20), Console.Color.Magenta, Console.Style.Bold))
    }

    /**
     * Presents a basic overview and guidelines directed to a user
     */
    fun guide() {
        println("You are a train manager. Don't worry, here it's easier than ever.")
        println("The process of forming and sending the trains is fully automatic.")
        println("You only decide whether you want to send a new train to a new direction or stop the application.\n")
    }

    /**
     * Starts and runs the application, as well as handles the whole process, providing general event notifications
     */
    fun start() {
        println(Console.format("The application is started", Console.Color.Magenta, Console.Style.Bold))
        while (true) {
            val manager = Manager()
            val proxy = ManagerProxy(manager)
            proxy.route = proxy.createRoute()
            println()

            proxy.tickets = proxy.sellTickets(bookingOffice)
            println()

            proxy.train = proxy.makeUpTrain()
            println("The train is ready to set off!")
            println()

            proxy.isLeft = proxy.setTrain()
            proxy.isArrived = proxy.sendTrain(
                (proxy.route.distance / 1000).toInt() * 10, 1000, "clickety-clack"
            )
            println(Console.format("\nThe train ${proxy.route.value.first.name} - " +
                    "${proxy.route.value.second.name} is at the place!",
                Console.Color.Green,
                Console.Style.Bold, Console.Style.Underlined)
            )
            println()

            print(Console.format("Press Enter to create a new route " +
                "(to stop here print \"$stopWord\" before pressing the Enter key): ",
                Console.Style.Bold))

            if (readln().lowercase() == stopWord) break
        }
    }

    /**
     * Presents a message notifying the application has been stopped
     */
    fun stop() {
        println(Console.format("The application is stopped", Console.Color.Magenta, Console.Style.Bold))
    }
}
