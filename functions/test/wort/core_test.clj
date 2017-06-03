(ns wort.core-test
  (:require [clojure.test :refer :all]
            [wort.core :refer :all]))

(deftest a-test
  (testing "FIXME, I fail."
    (is (= 1 1))))

(deftest _get_words_from_key
  (testing "splits on various permutations"
    (is (= ["a" "test"] (get_words_from_key {"key" "a test"} "key" )))
    (is (= ["a" "test"] (get_words_from_key {"key" "  a  test  "} "key" )))
    (is (= ["a" "test"] (get_words_from_key {"key" "a test!"} "key" )))
    (is (= ["a" "test"] (get_words_from_key {"key" "a...test!"} "key" )))
    (is (= ["a" "test"] (get_words_from_key {"key" " !!!!a...!!!!!test! "} "key" )))
    (is (= ["This" "is" "a" "test"] (get_words_from_key {"key" "This, is a test!"} "key" )))))
