(ns wort.core
  (:gen-class
   :methods [^:static [handler [String] String]]))

(defn -handler
  [s]
  (str "Wort " s " ツ"))
