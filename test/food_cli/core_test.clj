(ns food-cli.core-test
  (:require [clojure.test :refer :all]
            [food-cli.core :refer :all]
            [clj-time.core :as time]
            [clj-time.local :as local]))


(deftest get-loc-test
  (testing "get the location"
    (testing "if the String is right"
      (is (= (get-location "erba") "ERBA"))
      (is (= (get-location "feki") "FEKI")))
    
    (testing "if the String is wrong" 
       (is (thrown? Exception  (get-location "notWhatIWant"))))))

(deftest date-formatter-test
  (testing "get "
    (testing "the date is right"
     (is (= (date-formatter (time/date-time 2018 01 01)) "2018/01/01"))
     (is (= (date-formatter (time/date-time 2018 02 28)) "2018/02/28")))
     
    
    (testing "the wrong date format"
      (not (= (date-formatter (time/date-time 2018 01 01)) "01/01/2018")))))
   

(deftest get-the-right-url-test
  (testing "get"
    (testing "the right url"
      (is (= (get-the-right-url ["feki" "01" "01" "2018"]) "https://mg-server.ddns.net/app/api/v1.1/food/FEKI/2018/01/01"))
      (is (= (get-the-right-url ["erba" "01" "01" "2018"]) "https://mg-server.ddns.net/app/api/v1.1/food/ERBA/2018/01/01"))
      (is (= (get-the-right-url ["feki"]) (str "https://mg-server.ddns.net/app/api/v1.1/food/FEKI/" (date-formatter (local/local-now)))))
      (is (= (get-the-right-url ["erba"]) (str "https://mg-server.ddns.net/app/api/v1.1/food/ERBA/" (date-formatter (local/local-now)))))
      (is (= (get-the-right-url []) (str "https://mg-server.ddns.net/app/api/v1.1/food/ERBA/" (date-formatter (local/local-now))))))
    
   (testing "wrong url"  
     (not (= (get-the-right-url ["feki" "01" "01" "2018"]) "https://mg-server.ddns.net/app/api/v1.1/food/FEKI/01/01/2018"))
     (is (= (get-the-right-url ["feki" "01" "01"]) "Please try again!")))))
      
      
            
      
    
    
    
    
    

  

