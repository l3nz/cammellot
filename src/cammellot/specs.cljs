(ns cammellot.specs
  (:require [orchestra-cljs.spec.test :as orchestra]
            [clojure.spec.alpha :as s]))


;
; Un personaggio
;


(s/def ::id int?)
(s/def ::anno-nascita int?)
(s/def ::sesso #{:m :f})
(s/def ::lavoro #{:contadino :soldato :chierico})

(s/def ::persona (s/keys :req-un [::id ::anno-nascita ::sesso ::lavoro]))
(s/def ::persone (s/coll-of ::persona))

;
; Un campo
;

(s/def ::coltura #{:incolto :grano})

(s/def ::campo (s/keys :req-un [::coltura]))
(s/def ::campi (s/coll-of ::campo))

(s/def :anno int?)

(orchestra/instrument)

