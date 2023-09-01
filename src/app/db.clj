(ns app.db
  (:require [xtdb.api :as xt]
            [mount.core :refer [defstate]]))

(defn start-xtdb! [] ; from XTDB’s getting started: xtdb-in-a-box
  (assert (= "true" (System/getenv "XTDB_ENABLE_BYTEUTILS_SHA1")))
  (letfn [(kv-store [dir] {:kv-store {:xtdb/module 'xtdb.rocksdb/->kv-store
                                      :db-dir (clojure.java.io/file dir)
                                      :sync? true}})]
    (xt/start-node
     {:xtdb/tx-log (kv-store (or (System/getenv "XTDB_TX_LOG_PATH")
                                 "data/dev/tx-log"))
      :xtdb/document-store (kv-store (or (System/getenv "XTDB_DOCUMENT_STORE_PATH")
                                         "data/dev/doc-store"))
      :xtdb/index-store (kv-store (or (System/getenv "XTDB_INDEX_STORE_PATH")
                                      "data/dev/index-store"))})))

(defstate node
  :start (start-xtdb!)
  :stop (.close node))

(defn db []
  (xt/db node))
