/**
 * Created by andrj148 on 5/26/16.
 */

class Amplifier() {
    fun on() {
        println("turn on Amplifier")
    }
    fun off() {
        println("turn off Amplifier")
    }
}

class DVDPlayer() {
    fun on() {
        println("turn on DVD Player")
    }
    fun off() {
        println("turn off DVD Player")
    }
    fun play() {
        println("play movie")
    }
}

class Projector() {
    fun on() {
        println("turn on Projector")
    }
    fun off() {
        println("turn off Projector")
    }
    fun wideScreen() {
        println("switch projector to wide screen mode")
    }
}

data class TheaterLights(var lightLevel: Int) {
    fun dim(lightLevel: Int) {
        println("dim lights to level $lightLevel")
    }
}

class Screen() {
    fun down() {
        println("Big screen comes down")
    }
    fun up() {
        println("Big screen goes up")
    }
}

class SurroundSound() {
    fun on() {
        println("turn on surround sound")
    }
    fun off() {
        println("turn off surround sound")
    }
}

class PopcornPopper() {
    fun on() {
        println("turn on popcorn machine")
    }
    fun off() {
        println("turn off popcorn machine")
    }
    fun pop() {
        println("begin popping popcorn")
    }
}

class HomeTheaterFacade(var amp: Amplifier,
                        var dvd: DVDPlayer,
                        var projector: Projector,
                        var lights: TheaterLights,
                        var screen: Screen,
                        var sound: SurroundSound,
                        var popper: PopcornPopper) {

    fun watchMovie(movieTitle: String) {
        println("You're about to watch $movieTitle")
        popper.on()
        popper.pop()
        lights.dim(11)
        screen.down()
        projector.on()
        projector.wideScreen()
        amp.on()
        sound.on()
        dvd.on()
        dvd.play()
    }

}