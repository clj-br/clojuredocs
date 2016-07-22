(ns clojuredocs.handler
  (:require [compojure.core :refer [GET defroutes]]
            [compojure.route :refer [not-found resources]]
            [hiccup.page :refer [include-js include-css html5]]
            [clojuredocs.middleware :refer [wrap-middleware]]
            [config.core :refer [env]]))

(def mount-target
  [:div#app
   [:h3 "ClojureScript has not been compiled!"]
   [:p "please run "
    [:b "lein figwheel"]
    " in order to start the compiler"]])

(defn head []
  [:head
   [:meta {:charset "utf-8"}]
   [:meta {:http-equiv "X-UA-Compatible" :content= "IE=edge"}]
   [:meta {:name    "viewport"
           :content "width=device-width, initial-scale=1"}]
   [:meta {:name    "description"
           :content "ClojureDocs BR é o primeiro site colaborativo em Língua Portuguesa contendo a documentação traduzida da linguagem Clojure e exemplos para as suas funções e macros."}]

   (include-css (if (env :dev) "/css/site.css" "/css/site.min.css"))
   (include-css (if (env :dev) "/css/bootstrap.css" "/css/bootstrap.min.css"))
   (include-css (if (env :dev) "/css/bootstrap-theme.css" "/css/bootstrap-theme.min.css"))
   [:title "ClojureDocs BR"]])

(def loading-page
  (html5 {:lang "pt-BR"}
    (head)
    [:body {:class "body-container"}
     mount-target
     (include-js "/js/app.js")]))


(defroutes routes
           (GET "/" [] loading-page)
           (resources "/")
           (not-found "Not Found"))

(def app (wrap-middleware #'routes))
