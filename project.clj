(defproject timerpage "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [domina "1.0.1"]]
  :plugins [[lein-cljsbuild "0.2.9"]]
  :cljsbuild {:builds {:main {:source-path "src/cljs"}}})
