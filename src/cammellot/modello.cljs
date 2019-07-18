(ns cammellot.modello)


;
; Inizializza il gioco vuoto
;
;


(defn nuovo-gioco []
  {:anno 1000
   :terre []
   :persone []})

(defn avanza-anno
  [stato-precedente]

  {:anno (inc (:anno stato-precedente))
   :terre []
   :persone []})