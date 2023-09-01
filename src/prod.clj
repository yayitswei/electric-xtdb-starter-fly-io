(ns prod
  (:gen-class)
  (:require clojure.string
            app.todo-list
            app.electric-server-java8-jetty9
            mount.core))

(def electric-server-config
  {:host "0.0.0.0", :port 8080, :resources-path "public"})

(defn -main [& args] ; run with `clj -M -m prod`
  (when (clojure.string/blank? (System/getProperty "HYPERFIDDLE_ELECTRIC_SERVER_VERSION"))
    (throw (ex-info "HYPERFIDDLE_ELECTRIC_SERVER_VERSION jvm property must be set in prod" {})))
  (mount.core/start))
