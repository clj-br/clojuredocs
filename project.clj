(defproject clojuredocs "0.1.0-SNAPSHOT"
 :description "FIXME: write description"
 :url "http://example.com/FIXME"
 :license {:name "Eclipse Public License"
           :url  "http://www.eclipse.org/legal/epl-v10.html"}

 :dependencies [[org.clojure/clojure "1.8.0"]
                [ring-server "0.4.0"]
                [reagent "0.6.0-rc"]
                [reagent-forms "0.5.24"]
                [reagent-utils "0.1.9"]
                [ring "1.5.0"]
                [ring/ring-defaults "0.2.1"]
                [compojure "1.5.1"]
                [hiccup "1.0.5"]
                [yogthos/config "0.8"]
                [org.clojure/clojurescript "1.9.93"
                 :scope "provided"]
                [secretary "1.2.3"]
                [venantius/accountant "0.1.7"
                 :exclusions [org.clojure/tools.reader]]
                [migratus "0.8.28"]
                [org.postgresql/postgresql "9.4.1207"]]

 :plugins [[lein-environ "1.0.2"]
           [lein-cljsbuild "1.1.1"]
           [lein-asset-minifier "0.2.7"
            :exclusions [org.clojure/clojure]]
           [org.clojure/core.cache "0.6.5"]
           [lein-ring "0.9.7"]
           [migratus-lein "0.4.1"]]

 :ring {:handler      clojuredocs.handler/app
        :uberwar-name "clojuredocs.war"}

 :min-lein-version "2.5.0"

 :uberjar-name "clojuredocs.jar"

 :main clojuredocs.server

 :clean-targets ^{:protect false}
 [:target-path
  [:cljsbuild :builds :app :compiler :output-dir]
  [:cljsbuild :builds :app :compiler :output-to]]

 :source-paths ["src/clj" "src/cljc"]
 :resource-paths ["resources" "target/cljsbuild"]

 :minify-assets
 {:assets
  {"resources/public/css/site.min.css"
   "resources/public/css/site.css"
   "resources/public/css/bootstrap.min.css"
   "resources/public/css/bootstrap-theme.min.css"}}

 :cljsbuild
 {:builds {:min
           {:source-paths ["src/cljs" "src/cljc" "env/prod/cljs"]
            :compiler
                          {:output-to     "target/cljsbuild/public/js/app.js"
                           :output-dir    "target/uberjar"
                           :optimizations :advanced
                           :pretty-print  false}}
           :app
           {:source-paths ["src/cljs" "src/cljc" "env/dev/cljs"]
            :compiler
                          {:main          "clojuredocs.dev"
                           :asset-path    "/js/out"
                           :output-to     "target/cljsbuild/public/js/app.js"
                           :output-dir    "target/cljsbuild/public/js/out"
                           :source-map    true
                           :optimizations :none
                           :pretty-print  true}}
           :test
           {:source-paths ["src/cljs" "src/cljc" "test/cljs"]
            :compiler     {:main          clojuredocs.doo-runner
                           :asset-path    "/js/out"
                           :output-to     "target/test.js"
                           :output-dir    "target/cljstest/public/js/out"
                           :optimizations :whitespace
                           :pretty-print  true}}}}

 :migratus {:store :database
            :migration-dir "migrations"
            :db ~(get (System/getenv) "DATABASE_URL")}

 :figwheel
 {:http-server-root "public"
  :server-port      3449
  :nrepl-port       7002
  :nrepl-middleware ["cemerick.piggieback/wrap-cljs-repl"
                     "cider.nrepl/cider-middleware"
                     "refactor-nrepl.middleware/wrap-refactor"]

  :css-dirs         ["resources/public/css"]
  :ring-handler     clojuredocs.handler/app}

 :sass {:src "src/sass"
        :dst "resources/public/css"}

 :profiles {:dev     {:repl-options {:init-ns clojuredocs.repl}

                      :dependencies [[ring/ring-mock "0.3.0"]
                                     [ring/ring-devel "1.5.0"]
                                     [prone "1.1.1"]
                                     [figwheel-sidecar "0.5.4-5"]
                                     [org.clojure/tools.nrepl "0.2.12"]
                                     [com.cemerick/piggieback "0.2.2-SNAPSHOT"]
                                     [lein-doo "0.1.6"]
                                     [pjstadig/humane-test-output "0.8.0"]]

                      :source-paths ["env/dev/clj"]
                      :plugins      [[lein-figwheel "0.5.4-5"]
                                     [lein-doo "0.1.6"]
                                     [cider/cider-nrepl "0.10.0-SNAPSHOT"]
                                     [org.clojure/tools.namespace "0.3.0-alpha2"
                                      :exclusions [org.clojure/tools.reader]]
                                     [refactor-nrepl "2.0.0-SNAPSHOT"
                                      :exclusions [org.clojure/clojure]]

                                     [lein-sassy "1.0.7"]]

                      :injections   [(require 'pjstadig.humane-test-output)
                                     (pjstadig.humane-test-output/activate!)]

                      :env          {:dev true
                                     :database-url "postgres://localhost:5432/clojuredocs_development"}}}

  :test {    :env {:database-url "postgres://localhost:5432/clojuredocs_test"}}

   :uberjar {:hooks        [minify-assets.plugin/hooks]
             :source-paths ["env/prod/clj"]
             :prep-tasks   ["compile" ["cljsbuild" "once" "min"]]
             :env          {:production true}
             :aot          :all
             :omit-source  true}

 :jvm-opts ^:replace ["-server"
                      "-Xms1g"
                      "-Xmx1g"
                      "-XX:-OmitStackTraceInFastThrow"
                      "-XX:+UseConcMarkSweepGC"])
