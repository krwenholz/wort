(defproject wort "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/data.json "0.2.6"]
                 [com.amazonaws/aws-lambda-java-core "1.0.0"]
                 [uswitch/lambada "0.1.2"]
                 [pjstadig/humane-test-output "0.8.2"]
                 [compojure "1.6.0"]
                 [deraen/sass4clj "0.3.1"]
                 [ring/ring-defaults "0.2.1"]]
  :sass4clj {:source-paths ["resources/public/css/scss"]
             :target-path "resources/public/css"
             :output-style :compressed}
  :ring {:handler wort.handler/app}
  :injections [(require 'pjstadig.humane-test-output)
               (pjstadig.humane-test-output/activate!)]
  :plugins [[lein-cljfmt "0.5.6"]
	        [lein-ring "0.12.1"]
            [deraen/lein-sass4clj "0.3.1"]]
  :main ^:skip-aot wort.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
