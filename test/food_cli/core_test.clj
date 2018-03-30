(ns food-cli.core-test
  (:require [clojure.test :refer :all]
            [food-cli.core :refer :all]
            [clj-time.core :as time]))


(deftest get-loc-test
  (testing "get the right location"
    (testing "if the String is right"
      (is (= (get-location "erba") "ERBA"))
      (is (= (get-location "feki") "FEKI")))
    
    (testing "if the String is wrong" 
      (not (= (get-location "notTheString") "ERBA")))))

(deftest date-formatter-test
  (testing "get the right date"
    (testing "the date is right"
     (is (= (date-formatter (time/date-time 2018 01 01)) "2018/01/01")))))
      
    
    
    
    
    

  

