# timerpage

A simple web page that counts down to a particular clock time, turning
green at ten minutes, yellow at five, and red at one. Also flashes for
ten seconds at transitions, and always after one minute remaining.

I wrote this to communicate time remaining to the speakers at
Clojure/conj 2012. The code is pretty crap, but it has worked really
well.

Things I would like to fix:

* Allow the user to specify times other than ten, five, and one minute
  remaining.
* Use CSS transitions rather than using ClojureScript to toggle CSS
  styles.
* Add doc strings.
* Add a stop button.

## Usage

* Visit [http://candera.github.com/timerpage](http://candera.github.com/timerpage)

**OR**

* Put `index.html`, `main.js`, and `timerpage.css` in the same directory
* Open index.html
* Enter a time like "15:33"
* Click Start

## License

Copyright © 2012 Craig Andera

Distributed under the Eclipse Public License, the same as Clojure.
