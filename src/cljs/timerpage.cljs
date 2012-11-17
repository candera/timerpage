(ns timerpage
  (:require [domina :as d]
            [domina.events :as e]
            [goog.Timer :as Timer]))

(def running (atom false))
(def stop-time (atom nil))

(defn format-time [t]
  (let [m (/ t 60)
        s (mod t 60)]
    (if (or (neg? m) (neg? s))
      "00:00"
      (format "%02d:%02d" m s))))

(defn minutes [s]
  (* 60 s))

(defn tick [e]
  (when @running
    (let [now (js/Date.)
          ms-remaining (- @stop-time now)
          back-half? (> 500 (Math/abs (mod ms-remaining 1000)))
          seconds-remaining (/ ms-remaining 1000)
          formatted-t (format-time seconds-remaining)]
      (d/set-text! (d/by-id "time") formatted-t)
      (d/set-classes! 
       (.-body js/document)
       (cond
        (> seconds-remaining (minutes 10)) "more-than-ten"
        (> seconds-remaining (- (minutes 10) 10)) ["ten-or-less" "alerting"]
        (> seconds-remaining (minutes 5)) "ten-or-less"
        (> seconds-remaining (- (minutes 5) 10)) ["five-or-less" "alerting"]
        (> seconds-remaining (minutes 1)) "five-or-less"
        :else ["one-or-less" "alerting"]))
      (d/add-class!
       (.-body js/document)
       (if back-half? "up" "down")))))

(defn start-timer [e]
  (let [[_ h m] (-> (d/by-id "finish")
                    (d/value)
                    (.match #"(\d+):(\d+)"))
        now (js/Date.)
        t (js/Date. (.getFullYear now)
                    (.getMonth now)
                    (.getDate now)
                    h 
                    m)]
    (.log js/console t)
    (reset! stop-time t)
    (reset! running true)))

(defn ^:export start [] 
  (let [t (goog.Timer. 200)]
    (e/listen! t Timer/TICK tick)
    (.start t))
  (reset! stop-time [23 59])
  (e/listen! (d/by-id "start")
             :click
             start-timer))