(ns clojuredocs.db.core
 (:require [clojure.java.jdbc :as jdbc]
           [mount.core :as mount]
           [conman.core :as conman]
           [environ.core :refer [env]]))

(mount/defstate ^:dynamic *db*
                 :start (conman/connect! {:jdbc-url (env :database-url)
                                          :adapter "postgresql"})
                 :stop  (conman/disconnect! *db*))

(conman/bind-connection *db* "sql/queries.sql")
