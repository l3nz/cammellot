(ns cammellot.cfg
  (:require [reagent.core :as reagent]))

; L'atomo dello stato


(defonce app-state (reagent/atom {}))

; Le impostazioni

(def ANNO-INIZIALE 1000)

(def CAMPI-TOTALI 10)
(def CAMPI-COLTIVATI-SETUP 2)

(def CONTADINI-INIZIALI 10)






