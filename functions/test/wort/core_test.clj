(ns wort.core-test
  (:require [clojure.test :refer :all]
            [wort.core :refer :all]))

(deftest split-words-splits-on-whitespace
  (is (= ["a" "test"] (split-words "a test")))

  (is (= [] (split-words "")))

  (is (= ["a" "test"] (split-words "  a  test  "))))

(deftest split-words-splits-on-punctuation
  (is (= ["a" "test"] (split-words " !!!!a...!!!!!test! ")))

  (is (= ["This" "is" "a" "test"] (split-words "This, is a test!")))

  (is (= ["OMG" "Sally" "is" "like" "all" "about" "that" "money" "Maybe" "or" "may" "not" "be" "true"]
         (split-words "OMG!!!! Sally, is, like, all about that $money,,... *Maybe or may not be true"))))
