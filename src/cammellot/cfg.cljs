(ns cammellot.cfg
  (:require [reagent.core :as reagent]))

; L'atomo dello stato


(defonce app-state (reagent/atom {}))

; Le impostazioni

(def ANNO-INIZIALE 1000)


