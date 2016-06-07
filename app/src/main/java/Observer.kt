/**
 * Created by andrj148 on 6/6/16.
 */

// https://github.com/fcostaa/kotlin-designpatterns/blob/master/designpatterns/gangoffour/src/main/kotlin/com/felipecosta/designpatterns/structural/observer/Observer.kt

interface Observer {
    fun update(subject: WeatherData)
}

open class WeatherData() {

    var temperature: Int = 0
        set(value) {
            field = value
            notifyObservers()
        }

    var windChill: Int = 0

    val observers: MutableList<Observer> = mutableListOf()

    fun setNewWindChill(newWind: Int) {
        windChill = newWind
        notifyObservers()
    }

    fun attach(observer: Observer) {
        observers.add(observer)
    }

    fun detach(observer: Observer) {
        observers.remove(observer)
    }

    fun notifyObservers() {
        observers.forEach { it.update(this) }
    }
}

class WindChillObserver(): Observer {

    override fun update(subject: WeatherData) {
        println("\t New Wind chill: $subject.windChill")
    }
}

class TempObserver(): Observer {

    override fun update(subject: WeatherData) {
        println("\t New temperature: $subject.temperature} ")
    }
}

/*
        val weatherObject = WeatherData()

        val windChillObserver = WindChillObserver()
        val temperatureObserver = TempObserver()
        weatherObject.attach(windChillObserver)
        weatherObject.attach(temperatureObserver)

        println("First temperature change: 75")
        weatherObject.temperature = 75
        println("Second temperature change: 90")
        weatherObject.temperature = 90
        println("First wind chill change: 12")
        weatherObject.setNewWindChill(12)

        weatherObject.detach(windChillObserver)
        weatherObject.detach(temperatureObserver)
 */