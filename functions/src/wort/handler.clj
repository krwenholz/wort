(ns wort.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.util.response :as resp]
            [wort.core :as core]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defroutes app-routes
  (GET "/" []
    (resp/content-type (resp/resource-response "index.html" {:root "public"}) "text/html"))
  (GET "/sound" []
    (resp/file-response "../resources/a-team_crazy_fool_x.wav"))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
