(ns clojuredocs.doo-runner
  (:require [doo.runner :refer-macros [doo-tests]]
            [clojuredocs.core-test]))

(doo-tests 'clojuredocs.core-test)
