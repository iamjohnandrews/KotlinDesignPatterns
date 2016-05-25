
/**
 * Created by andrj148 on 5/23/16.
 */

//use abstract classes instead of interface because interface cannot have default code implementation, like protocol extensions in Swift
/*
It should be pretty clear that reducing dependencies to concrete classes in our code is a “good thing.”
In fact, we’ve got an OO design principle that formalizes this notion; it even has a big, formal name: Dependency Inversion Principle.
Yet another phrase you can use to impress the execs in the room! Your raise will more than offset the cost of this book, and you’ll gain the admiration of your fellow developers.
Here’s the general principle:
Depend upon abstractions. Do not depend upon concrete classes.
*/
/*
abstract class Pizza() {
    open var name: String = "name will be decided by subclass"
    open var dough: String = "dough will be decided by subclass"
    open var sauce: String = "sauce will be decided by subclass"
    open var toppings = arrayListOf<String>()

    fun prepare() {
        println("Preparing $name type of pizza")
        println("Tossing $dough type of dough")
        println("Adding $sauce type of sauce")

        for (toppingType in toppings) {
            println("Add $toppingType topping")
        }
    }
    fun bake() {
        println("Bake for 40 min")
    }
    open fun cut() {
        println("Cut pizza into square slices")
    }
    fun box() {
        println("Place pizza into official PizzaStore box")
    }
}
*/
abstract class PizzaStore {
    abstract fun createPizza(type: String): Pizza

    fun orderPizza(type: String): Pizza {
        val pizza = createPizza(type)

        pizza.prepare()
        pizza.bake()
        pizza.cut()
        pizza.box()

        return pizza
    }
}
/*
class NYPizzaStore(): PizzaStore() {
    override fun createPizza(type: String): Pizza {
        var nyStylePizza: Pizza

        if (type == "cheese") {
            nyStylePizza = NYStyleCheesePizza()
        } else {
            nyStylePizza = BasicPizza()
        }
        return nyStylePizza
    }
}

class ChicagoPizzaStore(): PizzaStore() {
    override fun createPizza(type: String): Pizza {
        var chicagoStylePizza: Pizza

        if (type == "cheese") {
            chicagoStylePizza = ChicagoStyleCheesePizza()
        } else {
            chicagoStylePizza = BasicPizza()
        }
        return chicagoStylePizza
    }
}

class NYStyleCheesePizza(): Pizza() {
    override var name = "NY Style Sauce and Cheese Pizza"
    override var dough = "Thin Crust Dough"
    override var sauce = "Marinara Sauce"

    override var toppings = super.toppings
        set(value) {
            super.toppings = arrayListOf("Grated Reggiano Cheese")
        }

    override fun cut() {
        println("Cut pizza into diagonal slices")
    }
}

class ChicagoStyleCheesePizza(): Pizza() {
    override var name = "Chicago Style Deep Dish Pizza"
    override var dough = "Extra Thick Crust Dough"
    override var sauce = "Plum Tomato Sauce"

    override var toppings = super.toppings
        set(value) {
            super.toppings = arrayListOf("Shredded Mozzarella Cheese")
        }
}

class BasicPizza(): Pizza() {
    override var name = "Basic Pizza"
    override var dough = "Basic Dough"
    override var sauce = "Basic Sauce"
}
*/
//Abstract Ingredients
/*
We provided a means of creating a family of ingredients for pizzas by introducing a new type of factory called an Abstract Factory.
An Abstract Factory gives us an interface for creating a family of products. By writing code that uses this interface, we decouple our code from the actual factory that creates the products. That allows us to implement a variety of factories that produce products meant for different contexts—such as different regions, different operating systems, or different look and feels.
Because our code is decoupled from the actual products, we can substitute different factories to get different behaviors (like getting marinara instead of plum tomatoes).
An Abstract Factory provides an interface for a family of products. What’s a family? In our case, it’s all the things we need to make a pizza: dough, sauce, cheese, meats, and veggies.
From the abstract factory, we derive one or more concrete factories that produce the same products, but with different implementations.
We then write our code so that it uses the factory to create products. By passing in a variety of factories, we get a variety of implementations of those products. But our client code stays the same.
*/

abstract class PizzaIngrediantFactory {
    abstract fun createDough(): String
    abstract fun createSauce(): String
    abstract fun createCheese(): String
    abstract fun createToppings(): kotlin.collections.List<String>
}

class ChicagoPizzaIngrediantFactory: PizzaIngrediantFactory() {
    override fun createCheese(): String {
        return "Shredded Mozzarella Cheese"
    }

    override fun createDough(): String {
        return "Extra Thick Crust Dough"
    }

    override fun createSauce(): String {
        return "Plum Tomato Sauce"
    }

    override fun createToppings(): kotlin.collections.List<String> {
        return kotlin.collections.arrayListOf("Meat", "Onion", "Red & Green Peppers")
    }
}

class NYPizzaIngrediantFactory: PizzaIngrediantFactory() {
    override fun createCheese(): String {
        return "Reggiano Cheese"
    }

    override fun createDough(): String {
        return "Thin Crust Dough"
    }

    override fun createSauce(): String {
        return "Marinara Sauce"
    }

    override fun createToppings(): kotlin.collections.List<String> {
        return kotlin.collections.arrayListOf("Onion", "Red Pepper", "Pepperoni")
    }
}

abstract class Pizza() {

    abstract var name: String
    abstract var dough: String
    abstract var sauce: String
    abstract var cheese: String
    abstract var toppings: kotlin.collections.List<String>
    abstract var title: String

    abstract fun prepare()
    fun bake() {
        println("Bake for 40 min")
    }
    open fun cut() {
        println("Cut pizza into square slices")
    }
    fun box() {
        println("Place pizza into official PizzaStore box")
    }
}

class BasicPizza(): Pizza() {
    override var name = "Pizza"
    override var dough = "Basic Dough"
    override var sauce = "Basic Sauce"
    override var cheese = "American Cheese"
    override var toppings = listOf("Seasoning")
    override var title = "Basic Pizza"

    override fun prepare() {
        println("Preparing a $name with $dough, $sauce, $cheese, and some $toppings")
    }
}

class CheesePizza(pizzaIngrediantFactory: PizzaIngrediantFactory, title: String): Pizza() {

    override var name = "Cheese"
    override var dough = pizzaIngrediantFactory.createDough()
    override var sauce = pizzaIngrediantFactory.createSauce()
    override var cheese = pizzaIngrediantFactory.createCheese()
    override var toppings = pizzaIngrediantFactory.createToppings()
    override var title = title

    override fun prepare() {
        println("Preparing a $name Pizza with $dough Dough, $sauce Sauce, $cheese Cheese, and $toppings for Toppings")
    }
}

class NYPizzaStore(): PizzaStore() {
    override fun createPizza(type: String): Pizza {
        var nyStylePizza: Pizza
        val nyPizzaIngrediantFactory = NYPizzaIngrediantFactory()

        if (type == "Cheese") {
            nyStylePizza = CheesePizza(nyPizzaIngrediantFactory, "New York Style Cheese Pizza")
        } else {
            nyStylePizza = BasicPizza()
        }
        return nyStylePizza
    }
}

class ChicagoPizzaStore(): PizzaStore() {
    override fun createPizza(type: String): Pizza {
        var chicagoStylePizza: Pizza
        val chicagoPizzaIngrediantFactory = ChicagoPizzaIngrediantFactory()

        if (type == "Cheese") {
            chicagoStylePizza = CheesePizza(chicagoPizzaIngrediantFactory, "Chicago Deep Dish Pizza")
        } else {
            chicagoStylePizza = BasicPizza()
        }
        return chicagoStylePizza
    }
}