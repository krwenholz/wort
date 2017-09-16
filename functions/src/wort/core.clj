(ns wort.core
  (:gen-class
   :methods [^:static [phraseBuildingHandler [String] String]])
  (:require [clojure.string :as str]
            [clojure.java.io :as io]
            [clojure.data.json :as json]
            [uswitch.lambada.core :refer [deflambdafn]])
  (:import [java.nio ByteBuffer]
           [java.util.concurrent LinkedBlockingQueue]
           [java.io SequenceInputStream]
           [javax.sound.sampled
            AudioFileFormat$Type
            AudioFormat
            AudioFormat$Encoding
            AudioInputStream
            AudioSystem]))

(defn split-words
  "Split a string into words."
  [string]
  (filter #(> (count %) 0) (str/split string #"[^\w]+")))

(defn grab-sound
	"Fetches the sound for a word."
	[word]
  (AudioSystem/getAudioInputStream (io/file "../resources/a-team_crazy_fool_x.wav")))

(defn append-sound
  [sound1 sound2]
  (AudioInputStream.
    (SequenceInputStream. sound1 sound2)
    (.getFormat sound1)
    (+ (.getFrameLength sound1) (.getFrameLength sound2))))

(defn write-audio-stream
  [out-stream audio]
  (AudioSystem/write audio AudioFileFormat$Type/WAVE out-stream))

(defn get-phrase
  [in]
  (in "phrase"))

(defn build-audio-phrase
  "Build the audio version of a text sentence."
  [in-stream out-stream]
  ;(do
  ;  ; AWS prints it as "{"phrase": "bob"}" but locally it's a map
  ;  (println (json/read (io/reader in-stream)))
  (->> (io/reader in-stream)
       (json/read)
       (get-phrase)
       (split-words)
       (map grab-sound)
       (reduce append-sound)
       (write-audio-stream out-stream)))

(deflambdafn wort.core.PhraseBuilder
  [in out ctx]
    (build-audio-phrase in out))
