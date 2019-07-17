(ns ^:figwheel-hooks cammellot.core
  (:require
   [goog.dom :as gdom]
   [reagent.core :as reagent :refer [atom]]
   [cammellot.grafica :as G]))


;; define your app data so that it doesn't get over-written on reload


(defonce app-state (atom {}))

(defn get-app-element []
  (gdom/getElement "app"))

(defn cammellot []
  [:div
   [:h1 "Benvenuti a Cammellotx!"]

   [:div {:class "flex-container"}

    (G/mostra-terra @app-state)
    (G/mostra-persone @app-state)
    (G/mostra-varie @app-state)
    (G/mostra-azioni @app-state)]])

(defn mount [el]
  (reagent/render-component [cammellot] el))

(defn mount-app-element []
  (when-let [el (get-app-element)]
    (mount el)))

;; conditionally start your application based on the presence of an "app" element
;; this is particularly helpful for testing this ns without launching the app
(mount-app-element)

;; specify reload hook with ^;after-load metadata
(defn ^:after-load on-reload []
  (mount-app-element)
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
  )
