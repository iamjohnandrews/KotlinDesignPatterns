/**
 * Created by andrj148 on 5/23/16.
 */

//open class Beverage() {
//    open var description = "Unknown Beverage"
//
//    open fun getBeverageDescription(): String {
//        return description
//    }
//    open fun cost(): Double = 1.00
//}

interface Beverage {
    fun getBeverageDescription(): String
    fun cost(): Double
}

class Expresso(): Beverage {
    override fun getBeverageDescription(): String {
        return "Expresso"
    }

    override fun cost(): Double = 1.99
}

class HouseBlend(): Beverage {
    override fun getBeverageDescription(): String {
        return "House Blend Coffee"
    }
    override fun cost(): Double = 0.89
}


abstract class CondimentDecorator(): Beverage {
    //Kotlin interfaces cannot have state, therefore no properties. So you must use a class to have a property
    abstract override fun getBeverageDescription(): String
}

class Mocha(var chosenBeverage: Beverage): CondimentDecorator() {
    var beverage = chosenBeverage

    override fun getBeverageDescription(): String = beverage.getBeverageDescription() + " +Mocha"
    override fun cost(): Double = beverage.cost() + 0.20
}
class Vanilla(var chosenBeverage: Beverage): CondimentDecorator() {
    var beverage = chosenBeverage

    override fun getBeverageDescription(): String = beverage.getBeverageDescription() + " +Vanilla"
    override fun cost(): Double = beverage.cost() + 0.67
}


//-------------------------
/*
val beverage = Expresso()
println("Regular ${beverage.getBeverageDescription()} costs $${beverage.cost()}")


var beverage2 = HouseBlend() as Beverage //must cast thus base object as base class
beverage2 = Mocha(beverage2)
beverage2 = Vanilla(beverage2)
println("${beverage2.getBeverageDescription()} is $0.20 extra because mocha and costs $${beverage2.cost()}")

        */