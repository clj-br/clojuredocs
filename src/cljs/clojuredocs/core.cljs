(ns clojuredocs.core
  (:require [reagent.core :as reagent :refer [atom]]
            [reagent.session :as session]
            [secretary.core :as sec :include-macros true]
            [accountant.core :as accountant]
            [clojuredocs.shared :refer [home-page]]))

;; -------------------------
;; Views

(defn show-namespace []
  [:b "namespace"]
  )

(defn current-page []
  [(session/get :current-page)])

;; -------------------------
(sec/defroute "/" []
              (session/put! :current-page #'home-page))

(sec/defroute "/:namespace" []
              (session/put! :current-page #'show-namespace))
;; -------------------------
;; Initialize app

(defn mount-root []
  (reagent/render [current-page] (.getElementById js/document "app")))

(defn init! []
  (accountant/configure-navigation!
    {:nav-handler
     (fn [path]
       (sec/dispatch! path))
     :path-exists?
     (fn [path]
       (sec/locate-route path))})
  (accountant/dispatch-current!)
  (mount-root))