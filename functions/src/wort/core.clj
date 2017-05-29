(ns wort.core
  (:gen-class
   :methods [^:static [handler [String] String]]))

(require '[clojure.string :as str])

(defn -handler
  [s]
  (str "Wort " s " ツ"))

(defn get_key_as_list
  [payload key]
  (str/split (get payload key) #"\s|[.,!?]")
)
