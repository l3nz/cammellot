(ns ^:figwheel-hooks cammellot.core
  (:require
   [goog.dom :as gdom]
   [reagent.core :as reagent]

   [cammellot.grafica :as G]
   [cammellot.modello :as M]
   [cammellot.cfg :as CFG]
   [cammellot.specs :as S]))


;; define your app data so that it doesn't get over-written on reload


(defn get-app-element []
  (gdom/getElement "app"))

(defn cammellot []
  [:div
   [:h1 "Benvenuti a Cammellot!"]

   [:div {:class "flex-container"}

    (G/mostra-terra CFG/app-state)
    (G/mostra-persone CFG/app-state)
    (G/mostra-varie CFG/app-state)
    (G/mostra-azioni CFG/app-state)]

   [:div (str "Stato: " @CFG/app-state)]])

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
