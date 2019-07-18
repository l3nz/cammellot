(ns cammellot.grafica
  (:require [cammellot.modello :as M]))

(defn mostra-terra [s]

  [:div {:class "box"}
   [:h2 {:class "leo"} "Terra"]])

(defn mostra-persone [s]

  [:div {:class "box"}
   [:h2  "Per$one"]])

(defn mostra-varie [s]

  [:div {:class "box"}
   [:h2 "Varie"]

   [:ul
    [:li (str "Anno:" (:anno @s))]]])

(defn mostra-azioni [s]

  [:div {:class "box"}
   [:h2 "Azioni"]

   [:input {:type "button" :value "Nuovo gioco!"
            :on-click
            #(reset! s (M/nuovo-gioco))}]

   [:input {:type "button" :value "Avanza anno"
            :on-click
            #(swap! s M/avanza-anno)}]])




