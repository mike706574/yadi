(ns leiningen.new.yadi
  (:use [leiningen.new.templates :only [renderer name-to-path sanitize-ns ->files]]))

(def render (renderer "yadi/templates"))

(defn yadi
  [name]
  (let [data {:name name
              :ns-name (sanitize-ns name)
              :sanitized (name-to-path name)}]
    (->files
     data
     ["src/clj/{{sanitized}}/main.clj" (render "main.clj" data)]
     ["src/clj/{{sanitized}}/service.clj" (render "service.clj" data)]
     ["src/clj/{{sanitized}}/system.clj" (render "system.clj" data)]
     ["test/clj/{{sanitized}}/system_test.clj" (render "system_test.clj" data)]
     ["dev/user.clj" (render "user.clj" data)]
     ["project.clj" (render "project.clj" data)]
     ["README.md" (render "README.md")]
     ["epl-v10.html" (render "epl-v10.html")]
     [".gitignore" (render ".gitignore")])))
