(defproject json2csv "0.1.0-SNAPSHOT"
            :description "This script can be used to extract info about portal keys from inventory in Ingress game."
            :url "https://www.ingress.com"
            :main ^:skip-aot json2csv.core
            :license {:name "Eclipse Public License"
                      :url  "http://www.eclipse.org/legal/epl-v10.html"}
            :dependencies [[org.clojure/clojure "1.6.0"]
                           [cheshire "5.5.0"]
                           [org.clojure/data.csv "0.1.2"]])
