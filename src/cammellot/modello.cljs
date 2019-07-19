(ns cammellot.modello
  (:require
   [cljs.spec.alpha :as s]
   [orchestra-cljs.spec.test :as orchestra]
   [cammellot.specs :as S]
   [cammellot.cfg :as cfg]
   [cammellot.utils :as U]))

(defonce ID-COUNTER (atom {:persona 0}))

(defn get-next-id
  "Ottiene il prossimo id unico per un tipo dato."
  [tipo]

  (let [nv (swap! ID-COUNTER update-in [tipo] inc)]
    (get nv tipo -1)))

;
; Persone
;


(defn ->Persona
  [anno-nascita sesso]

  {:id (get-next-id :persona)
   :anno-nascita anno-nascita
   :sesso sesso
   :lavoro :contadino})

(s/fdef
  ->Persona
  :ret ::S/persona)

(defn ->Persone
  [anno-nascita sesso numero]

  (mapv #(->Persona anno-nascita sesso) (range numero)))

(defn eta->tipo
  [anno persona]

  (let [eta (- anno (:anno-nascita persona))]
    (condp > eta
      15 :bambino
      30 :giovane
      47 :adulto
      :vecchio)))

(s/fdef
  eta->tipo
  :args (s/cat
         :anno     int?
         :persona  ::S/persona))

(defn setup-persone
  []

  (let [maschi (/ cfg/CONTADINI-INIZIALI 2)
        femmine (- cfg/CONTADINI-INIZIALI maschi)]

    (-> []
        (into (->Persone cfg/ANNO-INIZIALE :m maschi))
        (into (->Persone cfg/ANNO-INIZIALE :f femmine)))))




;
; Terre
;


(defn ->Campo
  [coltivazione]

  {:id (get-next-id :persona)
   :coltura coltivazione})

(s/fdef
  ->Campo
  :args (s/cat :coltivazione ::S/coltura)
  :ret ::S/campo)

(defn ->Campi
  [coltivazione numero]

  (mapv #(->Campo coltivazione) (range numero)))

(defn setup-campi
  []

  (-> []
      (into (->Campi :grano cfg/CAMPI-COLTIVATI-SETUP))
      (into (->Campi :incolto (- cfg/CAMPI-TOTALI cfg/CAMPI-COLTIVATI-SETUP)))))

(s/fdef
  setup-campi
  :ret ::S/campi)






;
; Inizializza il gioco vuoto
;
;


(defn nuovo-gioco []
  {:anno cfg/ANNO-INIZIALE
   :campi (setup-campi)
   :persone (setup-persone)
   :granai []

   :ftr-coltivazione 1.0
   :ftr-fertilita 1.0})

(defn avanza-anno
  [stato-precedente]

  (merge
   stato-precedente

   {:anno (inc (:anno stato-precedente))
    :campi (:campi stato-precedente)
    :persone (:persone stato-precedente)}))

(orchestra/instrument)
