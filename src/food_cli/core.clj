(ns food-cli.core
  (:gen-class)
  (:require [clj-time.core :as time]
            [clj-time.local :as local]
            [clj-http.client :as client]
            [clj-json.core :as json]))

(def url "https://mg-server.ddns.net/app/api/v1.1/food/")
(def locations #{"ERBA" "Erba" "erba" "FEKI" "Feki" "feki"})
(def positive-number (Exception. "Please use only postive numbers and numbers under 10 should be have a leading 0 and the numbers shouldnt be greater than 2049"))


(defn date-formatter [curr-date]
  (let [day (time/day curr-date)
        month (time/month curr-date)
        year (time/year curr-date)]
   (format "%4d/%02d/%02d" year month day)))
    

(defn get-location [loc]
  (if (get locations loc) (clojure.string/upper-case loc) (throw (Exception. "Please use feki or erba as a faculty"))))

(defn to-int [number]
     (cond 
       (and (>= (.length number) 2) (<= (.length number) 4)) (try
                                                               (if  (and (> (read-string number) 0) (< (read-string number) 2050)) (read-string number) (throw (positive-number)))
                                                               (catch Exception e
                                                                 (throw positive-number)))
       :default (throw positive-number)))
  
(defn right-date-format? [args]
  (let [[year month day] args
        date {:year (to-int year)
              :month (to-int month)
              :day (to-int day)}]
    (time/date-time (:year date) (:month date) (:day date))
    (format "%s/%s/%s"  year month day)))
   

(defn get-food 
  ([location]  (str url (get-location location) "/" (date-formatter (local/local-now))))
  ([location day month year] (format "%s%s/%s" url (get-location location) (right-date-format? [year month day]))))



(defn get-the-right-url [args]
  (cond             
    (empty? args) (get-food "erba")
    (= (count args) 1) (get-food (first args))
    (= (count args) 4) (get-food (first args) (second args) (nth args 2) (nth args 3))
    :default "Please try again!"))

(defn pretty-print [args]
  (if (empty? args) (println "on your questioned date is no mensa food please try another day") (println args)))

(defn -main [& args]
 (try
  (let [url (get-the-right-url args)
        response (client/get url)
        food (json/parse-string (:body response))]
    (pretty-print  (map #(get % "name") (get (get food 0) "menu"))))
  (catch Exception e
    (println (.getMessage e)))))
  
  

