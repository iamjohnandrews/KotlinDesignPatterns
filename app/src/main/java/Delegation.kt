/**
 * Created by andrj148 on 6/6/16.
 */

interface CoffeeShopDelegate {
    fun makeMyCoffee()
}

class Me(var coffeeDelegate: CoffeeShopDelegate) {

    fun whenIWakeUp() {
        coffeeDelegate.makeMyCoffee()
    }
}

class Starbucks(): CoffeeShopDelegate {

    override fun makeMyCoffee() {
        println("Slow Roast Coffee. Add Sugar and Cream. Over charge. Give to Customer")
    }
}
class TimHorton(): CoffeeShopDelegate {

    override fun makeMyCoffee() {
        println("Slow Roast Coffee. Add Sugar and Cream. Be super polite. Give to Customer")
    }
}
class DunkinDonuts(): CoffeeShopDelegate {

    override fun makeMyCoffee() {
        println("Slow Roast Coffee. Add Sugar and Cream. Ask if they want a donut. Give to Customer")
    }
}

/*
  val starbucks = Starbucks()
        val timHorton = TimHorton()
        val dunkinDonuts = DunkinDonuts()

        var janeDoe = Me(dunkinDonuts)
        janeDoe.whenIWakeUp()

        var johnDoe = Me(timHorton)
        johnDoe.whenIWakeUp()
 */