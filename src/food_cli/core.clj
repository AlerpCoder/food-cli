(ns food-cli.core
  (:gen-class)
  (:require [clj-time.core :as time]
            [clj-time.local :as local]
            [clj-http.client :as client]
            [clj-json.core :as json]))

(def url "https://mg-server.ddns.net/app/api/v1.1/food/")
(def locations #{"ERBA" "Erba" "erba" "FEKI" "Feki" "feki"})


(defn date-formatter [curr-date]
 (let [day (time/day curr-date)        
       month (time/month curr-date)
       year (time/year curr-date)]
  (format "%4d/%02d/%02d" year month day)))
    

(defn get-location [loc]
  "TODO: this needs rework cause i need to use more clojure way exception "
  (if (get locations loc) (clojure.string/upper-case loc) (throw (Exception. "Please use feki or erba as a faculty"))))

(defn get-food 
  ([location]  (str url (get-location location) "/" (date-formatter (local/local-now))))
  ([location day month year] (format "%s%s/%s/%s/%s" url (get-location location) year month day)))


(defn get-the-right-url [args]
  (cond             
    (empty? args) (get-food "erba")
    (= (count args) 1) (get-food (first args))
    (= (count args) 4) (get-food (first args) (second args) (nth args 2) (nth args 3))
    :default "Please try again, one of the function is called wrong"))


(defn -main [& args]
   (let [url (get-the-right-url args)
         response (client/get url)
         food (json/parse-string (:body response))]
     (println  (map #(get % "name") (get (get food 0) "menu")))))
  
  
  
  
  
      

