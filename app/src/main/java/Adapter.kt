/**
 * Created by andrj148 on 5/26/16.
 */

//Object Adapter
interface DuckActions {
    fun quack()
    fun fly()
}

class RegularDuck: DuckActions {
    override fun quack() {
        println("Regular Duck Quack")
    }

    override fun fly() {
        println("Regular Duck Flying")
    }
}

interface TurkeyActions {
    fun gobble()
    fun fly()
}

class RegularTurkey: TurkeyActions {
    override fun gobble() {
        println("Regular Turkey Gobble")
    }

    override fun fly() {
        println("Regular Turkey flying a lil bit")
    }
}

// Context: you;re short Duck objects and you want to use Turkey objects in their place
// SO you call duckAction methods (interface) but still get Turkey output
data class TurkeyAdapter(val turkey: RegularTurkey): DuckActions {
    override fun quack() {
        turkey.gobble()
    }

    override fun fly() {
        turkey.fly()
    }
}

data class Duckish(val duckish: DuckActions) {
    init {
        println("Thanksgiving is around the corner")
        duckish.quack()
        duckish.fly()
    }
}

/*

Here’s how the Client uses the Adapter
① The client makes a request to the adapter by calling a method on it using the target interface.
② The adapter translates the request into one or more calls on the adaptee using the adaptee interface.
③ The client receives the results of the call and never knows there is an adapter doing the translation. This is the point!
The Adapter Pattern’s role is to convert one interface into another
 */