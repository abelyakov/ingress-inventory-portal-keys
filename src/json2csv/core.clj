(ns json2csv.core
  (:require [cheshire.core :refer :all]
            [clojure.data.csv :as csv]
            [clojure.java.io :as io]))
;(def in-path "/home/andy/Downloads/getInventory.json")

;;for -> pipe
(defn ->filter [col func]
  (filter func col))

(defn ->map [col func]
  (map func col))

(defn ->sort-by [col func]
  (sort-by func col))

;;checks if item is portal key
(defn is-key? [x]
  (-> x
      (nth 2)
      (contains? "portalCoupler")))


(defn get-key-info [k]
  (get-in k [2 "portalCoupler"]))

;;read input json
(defn read-json [path]
  (-> path
      slurp
      parse-string
      (get-in ["gameBasket" "inventory"])
      (->filter is-key?)
      (->map get-key-info)
      (->sort-by first)
      (frequencies)
      (->map #(assoc (first %) "count" (second %)))))

(defn -main [& args]
  (let [in-path (first args)
        out-path (if (nil? (second args)) (str in-path ".out.csv") (second args))
        portal-keys (read-json in-path)]
    (when (not-empty portal-keys)
      (let [p-cols (-> portal-keys first keys)
            p (conj (map vals portal-keys) p-cols)]
        (with-open [out-file (io/writer out-path)]
          (csv/write-csv out-file p))))))




