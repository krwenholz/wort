(ns wort.core
  (:gen-class
   :methods [^:static [handler [String] String]]))

(require '[clojure.string :as str])

(defn -handler
  [s]
  (str "Wort " s " ツ"))

(defn get_words_from_key
  [payload key]
  (let [value (filter
               #(> (count %) 0) (str/split (payload key) #"[^\w]+"))]
    (do
      (println value)
      value)))
