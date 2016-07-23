(ns clojuredocs.shared)

(defn home-page []
  [:main {:id "app"}
   [:div {:class "container site-description"}
    [:p "ClojureDocs BR é o primeiro site colaborativo em Língua Portuguesa contendo a documentação traduzida
  da linguagem " [:a {:href "http://clojure.org/"} "Clojure"] " e exemplos para as suas funções e macros."]
    [:div {:class "row"}
     [:div {:class "col-md-6 col-xs-6 box"}
      [:div {:class "inner-box"}
       [:h3 "Consulte"]
       [:p "O ClojureDocs BR é um manual de referência em português, criado com o intuito de ajudar a
  popularizar a linguagem entre os lusófonos derrubando a barreira do idioma."]
       [:p "Procure por algum termo, navegue pelas categorias ou pelos namespaces organizados em ordem
  alfabética."]]]

     [:div {:class "col-md-6 col-xs-6 box"}
      [:div {:class "inner-box"}
       [:h3 "Colabore"]
       [:p "Adicione seus próprio exemplos, comente e ajude-nos a manter as informações sempre corretas,
  claras e completas."]
       [:p "A graça do ClojureDocs BR está na colaboração dos usuários. Alguns usuários aprendem ou tiram
  dúvidas, outros ajudam a ensinar, e no final todo mundo fica feliz."]]]]]])
