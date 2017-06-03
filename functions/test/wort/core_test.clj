(ns wort.core-test
  (:require [clojure.test :refer :all]
            [wort.core :refer :all]))

(deftest a-test
  (testing "FIXME, I fail."
    (is (= 1 1))))

(deftest test_get_words_from_key
  (testing "splits on various permutations"
    (is (= ["a" "test"] (get_words_from_key {"key" "a test"} "key" )))

    (is (= [] (get_words_from_key {"key" ""} "key" )))

    (is (= ["a" "test"] (get_words_from_key {"key" "  a  test  "} "key" )))

    (is (= ["a" "test"] (get_words_from_key {"key" "a test!"} "key" )))

    (is (= ["a" "test"] (get_words_from_key {"key" "a...test!"} "key" )))

    (is (= ["OMG" "Sally" "is" "like" "all" "about" "that" "money" "Maybe" "or" "may" "not" "be" "true"]
           (get_words_from_key {"key" "OMG!!!! Sally, is, like, all about that $money,,... *Maybe or may not be true"} "key" )))

    (is (= ["a" "test"] (get_words_from_key {"key" " !!!!a...!!!!!test! "} "key" )))

    (is (= ["This" "is" "a" "test"] (get_words_from_key {"key" "This, is a test!"} "key" )))))
