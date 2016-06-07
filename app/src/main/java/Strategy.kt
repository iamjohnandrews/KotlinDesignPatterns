/**
 * Created by andrj148 on 6/6/16.
 */

abstract class FlyBehavior() {
    abstract fun fly(): String
}

class CanFly(): FlyBehavior() {
    override fun fly(): String {
        return "can fly"
    }
}

class CannotFly(): FlyBehavior() {
    override fun fly(): String {
        return "cannot fly"
    }
}

open class Animal(val itCanFly: Boolean) {
    var flyThing: FlyBehavior = if (itCanFly) CanFly() else CannotFly()

    fun flyAttempt(): String {
        return flyThing.fly()
    }
}

class Mouse(): Animal(false) {}
class Hawk(): Animal(true) {}