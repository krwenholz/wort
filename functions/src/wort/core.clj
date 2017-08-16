(ns wort.core
  (:gen-class
   :methods [^:static [phraseBuildingHandler [String] String]])
  (:require [clojure.string :as str]
            [clojure.data.json :as json]
            [uswitch.lambada.core :refer [deflambdafn]]
            [dynne.sampled-sound :as sound]))

(defn split-words
  "Split a string into words."
  [string]
  (filter #(> (count %) 0) (str/split string #"[^\w]+")))

(defn grab-sound
	"Fetches the sound for a word. TODO: this should take a speaker arg at some point."
	[word]
  (sound/read-sound "../resources/a-team_crazy_fool_x.wav"))

(defn build-audio-phrase
  "Build the audio version of a text sentence."
  [input]
  ; TODO(wenholz): destructure the json payload in the argument
  (->> (input "phrase")
       (split-words)
       (map grab-sound)
       (reduce sound/append)))

(deflambdafn wort.core.PhraseBuilder
  [in out ctx]
  (build-audio-phrase (json/read-str in)))
