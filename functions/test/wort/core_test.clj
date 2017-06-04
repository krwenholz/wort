(ns wort.core-test
  (:require [clojure.test :refer :all]
            [wort.core :refer :all]))

(deftest a-test
  (testing "FIXME, I fail."
    (is (= 1 1))))

(deftest splits_on_whitespace
    (is (= ["a" "test"] (get_words_from_key {"key" "a test"} "key" )))

    (is (= [] (get_words_from_key {"key" ""} "key" )))

    (is (= ["a" "test"] (get_words_from_key {"key" "  a  test  "} "key" ))))

(deftest splits_on_punctuation
    (is (= ["a" "test"] (get_words_from_key {"key" " !!!!a...!!!!!test! "} "key" )))

    (is (= ["This" "is" "a" "test"] (get_words_from_key {"key" "This, is a test!"} "key" )))

    (is (= ["OMG" "Sally" "is" "like" "all" "about" "that" "money" "Maybe" "or" "may" "not" "be" "true"]
           (get_words_from_key {"key" "OMG!!!! Sally, is, like, all about that $money,,... *Maybe or may not be true"} "key" ))))
