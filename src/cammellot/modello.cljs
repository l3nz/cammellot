(ns cammellot.modello
  (:require
   [cljs.spec.alpha :as s]
   [orchestra-cljs.spec.test :as orchestra]
   [cammellot.specs :as S]))

(defonce ID-COUNTER (atom {:persona 0}))

(defn get-next-id
  "Ottiene il prossimo id unico per un tipo dato."
  [tipo]

  (let [nv (swap! ID-COUNTER update-in [tipo] inc)]
    (get nv tipo -1)))

(defn ->Persona
  [anno-nascita sesso]

  {:id (get-next-id :persona)
   :anno-nascita anno-nascita
   :sesso sesso
   :lavoro :contadino})

(s/fdef
  ->Persona
  :ret ::S/persona)

(defn eta->tipo
  [persona anno]

  (let [eta (- anno (:anno-nascita persona))]
    (condp > eta
      15 :bambino
      30 :giovane
      40 :adulto
      :vecchio)))

(s/fdef
  eta->tipo
  :args (s/cat
         :persona  ::S/persona
         :anno     int?))




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

(orchestra/instrument)
