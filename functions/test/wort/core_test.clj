(ns wort.core-test
  (:require [clojure.test :refer :all]
            [wort.core :refer :all]))

(deftest a-test
  (testing "FIXME, I fail."
    (is (= 1 1))))

(deftest a-test
  (testing "splits on sporadic whitespace"
    (is (= ["is" "this"] (get_key_as_vector {"wat" "  is  this  "} "wat" )))))
