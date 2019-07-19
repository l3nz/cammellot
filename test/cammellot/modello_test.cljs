(ns cammellot.modello-test
  (:require [cljs.test :refer-macros [deftest is are testing]]
            [cammellot.modello :refer [eta->tipo]]))

(deftest eta->tipo-test
  (are [nascita anno out]
       (= out (eta->tipo {:id 0 :anno-nascita nascita :sesso :m :lavoro :contadino} anno))

    1973 1974 :bambino))
