(ns cammellot.grafica
  (:require [cammellot.modello :as M]
            [orchestra-cljs.spec.test :as orchestra]
            [clojure.spec.alpha :as s]
            [cammellot.utils :as U]))

(defn statistica-eta [persone anno-corrente]
  (let [tipi (map (partial M/eta->tipo anno-corrente) persone)]

    (str "Gruppoi di età" (frequencies tipi))))

(defn recap-statistica
  [nome l chiave]
  (let [elementi (U/conta-per-gruppo l chiave)]
    [:div
     (str nome ":" elementi)]))

(defn mostra-terra [s]

  [:div {:class "box"}
   [:h2 {:class "leo"} "Terre"]

   (recap-statistica "Coltivazioni" (:campi @s) :coltura)])

(defn mostra-persone [s]

  [:div {:class "box"}
   [:h2  "Per$one"]

   (recap-statistica "Sesso" (:persone @s) :sesso)
   (recap-statistica "Lavoro" (:persone @s) :lavoro)
   (statistica-eta (:persone @s) (:anno @s))])

(defn mostra-varie [s]

  [:div {:class "box"}
   [:h2 "Varie"]

   [:ul
    [:li (str "Anno:" (:anno @s))]
    [:li (str "Fertilità:" (:ftr-fertilita @s))]
    [:li (str "Coltivazione:" (:ftr-coltivazione @s))]]])

(defn mostra-azioni [s]

  [:div {:class "box"}
   [:h2 "Azioni"]

   [:input {:type "button" :value "Nuovo gioco!"
            :on-click
            #(reset! s (M/nuovo-gioco))}]

   [:input {:type "button" :value "Avanza anno"
            :on-click
            #(swap! s M/avanza-anno)}]])

(orchestra/instrument)
