(ns food-cli.core-test
  (:require [clojure.test :refer :all]
            [food-cli.core :refer :all]))

(deftest a-test
  (testing "FIXME, I fail."
    (is (= 0 1))))

(deftest get-loc-test
  (testing "get the right location"
    (testing "if the String is right"
      (is (= (get-location "erba") "ERBA"))
      (is (= (get-location "feki") "FEKI")))
    
    (testing "if the String is wrong" 
      (not (= (get-location "notTheString") "ERBA")))))

(deftest date-formatter
  (testing "get the right datei"))
    
    
    

  

