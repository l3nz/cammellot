(ns cammellot.utils)

;
; Utilità generali
;

(defn conta-per-gruppo
  "Questa funzione riceve una sequenza di mappe.
  Aggrega e conta sulla base della chiave passata.

  (conta-per-gruppo [{:a :m} {:a :f}] :a)
  {:m 1, :f 1}

  "
  [l campo]

  (let [elementi (group-by campo l)]
    (into
     {}
     (map (fn [[k v]]
            [k (count v)])
          elementi))))