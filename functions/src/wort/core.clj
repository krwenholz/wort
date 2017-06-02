(ns wort.core
  (:gen-class
   :methods [^:static [handler [String] String]]))

(require '[clojure.string :as str])

(defn -handler
  [s]
  (str "Wort " s " ツ"))

(defn get_key_as_vector
  [payload key]
  (let [value (str/split (get payload key) #"\s|[.,!?]")]
    (do
      (println value)
      value)))
