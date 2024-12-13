import java.util.*
import kotlin.math.*

/* Необходимо составить программу, которая помогает пользователю составить план поезда.
 * После запуска программа спрашивает пользователя - хочет ли он закончить работу, или составить поезд.
 *
 * Есть 4 основных шага
 * в создании плана:
 * - Создать направление - создает направление для поезда (к примеру Бийск - Барнаул).
 *   Маршрут составляется случайным выбором двух городов из списка, состоящего, минимум, из 15-и городов.
 *   Начальная и конечная точки маршрута должны быть различны.
 * - Продать билеты - вы получаете рандомное кол-во пассажиров, которые купили билеты на это направление.
 *   Количество пассажиров находится в диапазоне от 5-и до 201
 * - Сформировать поезд - вы создаете поезд и добавляете ему столько вагонов
 *   (вместительность каждого вагона определяется рандомно и находится в диапазоне от 5 до 25),
 *   сколько хватит для перевозки всех пассажиров.
 *   То есть вы прибавляете к поезду по одному вагону рандомной вместительности до тех пор,
 *   пока не усадите в них всех пассажиров, купивших билеты
 * - Отправить поезд - вы отправляете поезд, после чего можете снова создать направление.
 *   Программа выдает запрос на окончание или продолжение работы.
 *   То есть программа работает до тех пор, пока пользователь не введет слово EXIT.
 *
 * После каждого этапа выдается соответствующая информация.
 * После 4-го шага программа сообщает, что поезд [направление], состоящий из [количество] вагонов отправлен.
 * Также выдается информация о вместимости каждого вагона и количестве пассажиров в каждом вагоне.
 *
 *  Программа не должна "падать" при вводе неправильных значений
 */

fun main() {
    try {
        val app = App("Welcome to Train Manager!", "exit")
        app.greet()
        app.guide()
        if (app.confirm()) app.start()
        app.stop()
    } catch (exc: Exception) {
        println(exc.message)
    }
}

/* Sample I/O
=== Output 1 ===
*********************************************
          Welcome to Train Manager!
*********************************************
You are a train manager. Don't worry, here it's easier than ever.
The process of forming and sending the trains is fully automatic.
You only decide whether you want to send a new train to a new direction or stop the application.

So, let's get started! Press Enter to continue (print "exit" before pressing the Enter key if you want to stop right now): exit
The application is stopped

=== Output 2 ===
*********************************************
          Welcome to Train Manager!
*********************************************
You are a train manager. Don't worry, here it's easier than ever.
The process of forming and sending the trains is fully automatic.
You only decide whether you want to send a new train to a new direction or stop the application.

So, let's get started! Press Enter to continue (print "exit" before pressing the Enter key if you want to stop right now):
The application is started
Step 1: Create a route
2024-12-13 14:06:28.988 INFO Set Route from Houston to Jacksonville, distance: 1320 km

Step 2: Sell tickets
2024-12-13 14:06:28.996 INFO Tickets sold: 35

Step 3: Make up a train
2024-12-13 14:06:28.997 INFO Set Train [Car 15/15]-[Car 6/6]-[Car 8/8]-[Car 6/23]
The train is ready to set off!

Step 4: Send the train
2024-12-13 14:06:28.997 INFO Departure
The train Houston - Jacksonville consisting of 4 cars with 35 passengers is setting off the terminal station
Houston Chug-chug!.....clickety-clack.....Toot-toot! Jacksonville
The train is arriving to the final station
2024-12-13 14:06:39.085 INFO Arrival

The train Houston - Jacksonville is at the place!

Press Enter to create a new route (to stop here print "exit" before pressing the Enter key):
Step 1: Create a route
2024-12-13 14:27:57.765 INFO Set Route from San Francisco to Denver, distance: 1524 km

Step 2: Sell tickets
2024-12-13 14:27:57.765 INFO Tickets sold: 49

Step 3: Make up a train
2024-12-13 14:27:57.767 INFO Set Train [Car 18/18]-[Car 17/17]-[Car 6/6]-[Car 8/24]
The train is ready to set off!

Step 4: Send the train
2024-12-13 14:27:57.767 INFO Departure
The train San Francisco - Denver consisting of 4 cars with 49 passengers is setting off the terminal station
San Francisco Chug-chug!.....clickety-clack.....Toot-toot! Denver
The train is arriving to the final station
2024-12-13 14:28:07.826 INFO Arrival

The train San Francisco - Denver is at the place!

Press Enter to create a new route (to stop here print "exit" before pressing the Enter key):
Step 1: Create a route
2024-12-13 14:28:25.361 INFO Set Route from Austin to Indianapolis, distance: 1490 km

Step 2: Sell tickets
2024-12-13 14:28:25.361 INFO Tickets sold: 139

Step 3: Make up a train
2024-12-13 14:28:25.361 INFO Set Train [Car 10/10]-[Car 16/16]-[Car 24/24]-[Car 20/20]-[Car 17/17]-[Car 10/10]-[Car 15/15]-[Car 23/23]-[Car 4/16]
The train is ready to set off!

Step 4: Send the train
2024-12-13 14:28:25.362 INFO Departure
The train Austin - Indianapolis consisting of 9 cars with 139 passengers is setting off the terminal station
Austin Chug-chug!.....clickety-clack.....Toot-toot! Indianapolis
The train is arriving to the final station
2024-12-13 14:28:35.464 INFO Arrival

The train Austin - Indianapolis is at the place!

Press Enter to create a new route (to stop here print "exit" before pressing the Enter key):
Step 1: Create a route
2024-12-13 14:29:21.924 INFO Set Route from Phoenix to New York, distance: 3443 km

Step 2: Sell tickets
2024-12-13 14:29:21.925 INFO Tickets sold: 139

Step 3: Make up a train
2024-12-13 14:29:21.927 INFO Set Train [Car 20/20]-[Car 23/23]-[Car 24/24]-[Car 14/14]-[Car 15/15]-[Car 25/25]-[Car 18/19]
The train is ready to set off!

Step 4: Send the train
2024-12-13 14:29:21.928 INFO Departure
The train Phoenix - New York consisting of 7 cars with 139 passengers is setting off the terminal station
Phoenix Chug-chug!.....clickety-clack.....clickety-clack.....clickety-clack.....clickety-clack.....clickety-clack.Toot-toot! New York
The train is arriving to the final station
2024-12-13 14:29:52.167 INFO Arrival

The train Phoenix - New York is at the place!

Press Enter to create a new route (to stop here print "exit" before pressing the Enter key):
Press Enter to create a new route (to stop here print "exit" before pressing the Enter key):
Step 1: Create a route
2024-12-13 14:39:30.933 INFO Set Route from New York to Phoenix, distance: 3443 km

Step 2: Sell tickets
2024-12-13 14:39:30.933 INFO Tickets sold: 7

Step 3: Make up a train
2024-12-13 14:39:30.934 INFO Set Train [Car 7/21]
The train is ready to set off!

Step 4: Send the train
2024-12-13 14:39:30.934 INFO Departure
The train New York - Phoenix consisting of 1 car with 7 passengers is setting off the terminal station
New York Chug-chug!.....clickety-clack.....clickety-clack.....clickety-clack.....clickety-clack.....clickety-clack.Toot-toot! Phoenix
The train is arriving to the final station
2024-12-13 14:40:01.233 INFO Arrival

The train New York - Phoenix is at the place!

Press Enter to create a new route (to stop here print "exit" before pressing the Enter key): exit
The application is stopped
 */
