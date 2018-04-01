(defproject food-cli "0.3.0-SNAPSHOT"
  :description "Get the mensa food from the university of bamberg"
  :url "https://github.com/alerpcoder"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :main food-cli.core
  :aot [food-cli.core]
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [clj-http "3.8.0"]                 
                 [clj-time "0.14.2"]
                 [clj-json "0.5.3"]
                 [slingshot "0.12.2"]])
