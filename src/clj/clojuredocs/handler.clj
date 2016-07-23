(ns clojuredocs.handler
  (:require [compojure.core :refer [GET defroutes]]
            [compojure.route :refer [not-found resources]]
            [hiccup.page :refer [include-js include-css html5]]
            [clojuredocs.middleware :refer [wrap-middleware]]
            [config.core :refer [env]]
            [clojuredocs.shared :refer [home-page]]))

(defn head []
  [:head
   [:meta {:charset "utf-8"}]
   [:meta {:http-equiv "X-UA-Compatible" :content= "IE=edge"}]
   [:meta {:name    "viewport"
           :content "width=device-width, initial-scale=1"}]
   [:meta {:name    "description"
           :content "ClojureDocs BR é o primeiro site colaborativo em Língua Portuguesa contendo a documentação traduzida da linguagem Clojure e exemplos para as suas funções e macros."}]

   (include-css "/css/bootstrap.css")
   ; (include-css (if (env :dev) "/css/bootstrap.css" "/css/bootstrap.min.css"))
   (include-css (if (env :dev) "/css/bootstrap-theme.css" "/css/bootstrap-theme.min.css"))
   (include-css (if (env :dev) "/css/site.css" "/css/site.min.css"))
   [:title "ClojureDocs BR"]])

(defn header []
  [:header
   [:div {:class "container"}
    [:a {:href "/"} "ClojureDocs BR"]]])

(defn footer []
  [:footer
   [:div {:class "container"}
    [:p "Este site não tem qualquer relação ou afiliação com o " [:a {:href "http://clojuredocs.org"} "ClojureDocs
  original"] ", apesar do original ter servido de inspiração e ponto de partida."]
    [:p "Clojure © 2008 Rich Hickey - http://clojure.org"]
    [:p "ClojureDocs © 2010 Zachary Kim - http://zacharykim.com"]
    [:p "ClojureDocs BR © 2013 Plínio Balduino / Clojure Brasil"]]])

(def loading-page
  (html5 {:lang "pt-BR"}
         (head)
         [:body {:role "document"}
          (header)
          [:main {:id "app"}
           (home-page)]
          (footer)
          (include-js "/js/app.js")]))


(defroutes routes
           (GET "/" [] loading-page)
           (resources "/")
           (not-found "Not Found"))

(def app (wrap-middleware #'routes))
