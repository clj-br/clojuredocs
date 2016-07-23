(ns clojuredocs.core
  (:require [reagent.core :as reagent :refer [atom]]
            [reagent.session :as session]
            [secretary.core :as secretary :include-macros true]
            [accountant.core :as accountant]))

;; -------------------------
;; Views

(defn home-page []
  [:main
   [:div {:class "container site-description"}
    [:p "ClojureDocs BR é o primeiro site colaborativo em Língua Portuguesa contendo a documentação traduzida
  da linguagem " [:a {:href "http://clojure.org/"} "Clojure"] "e exemplos para as suas funções e macros."]

    [:div {:class "row"}
     [:div {:class "col-md-6 col-xs-6 box"}
      [:div {:class "inner-box"}
       [:h3 "Consulte"]
       [:p "O ClojureDocs BR é um manual de referência em português, criado com o intuito de ajudar a
  popularizar a linguagem entre os lusófonos derrubando a barreira do idioma."]
       [:p "Procure por algum termo, navegue pelas categorias ou pelos namespaces organizados em ordem
  alfabética."]]]

     [:div {:class "col-md-6 col-xs-6 box"}
      [:div {:class "inner-box"}
       [:h3 "Colabore"]
       [:p "Adicione seus próprio exemplos, comente e ajude-nos a manter as informações sempre corretas,
  claras e completas."]
       [:p "A graça do ClojureDocs BR está na colaboração dos usuários. Alguns usuários aprendem ou tiram
  dúvidas, outros ajudam a ensinar, e no final todo mundo fica feliz."]]]]]])

(defn current-page []
  [:div [(session/get :current-page)]])

;; -------------------------
;; Routes

(secretary/defroute "/" []
                    (session/put! :current-page #'home-page))

;; -------------------------
;; Initialize app

(defn mount-root []
  (reagent/render [current-page] (.getElementById js/document "app")))

(defn init! []
  (accountant/configure-navigation!
    {:nav-handler
     (fn [path]
       (secretary/dispatch! path))
     :path-exists?
     (fn [path]
       (secretary/locate-route path))})
  (accountant/dispatch-current!)
  (mount-root))
