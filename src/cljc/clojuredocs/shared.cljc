(ns clojuredocs.shared)

(defn home-page []
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
  dúvidas, outros ajudam a ensinar, e no final todo mundo fica feliz."]]]]])

(defn footer []
  [:footer
   [:div {:class "container"}
    [:p "Este site não tem qualquer relação ou afiliação com o " [:a {:href "http://clojuredocs.org"} "ClojureDocs
  original"] ", apesar do original ter servido de inspiração e ponto de partida."]
    [:p "Clojure © 2008 Rich Hickey - " [:a {:href "http://clojure.org"} "http://clojure.org"]]
    [:p "ClojureDocs © 2010 Zachary Kim - " [:a {:href "http://zacharykim.com"} "http://zacharykim.com"]]
    [:p "ClojureDocs BR © 2013 Plínio Balduino / Clojure Brasil"]]])
