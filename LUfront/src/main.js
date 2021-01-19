import Vue from "vue";
import App from "./App.vue";
import router from './router'
import store from "./store";
import vuetify from './plugins/vuetify';
import firebase from 'firebase'

Vue.config.productionTip = false;

firebase.initializeApp({
    apiKey: "AIzaSyAleZJazRisTJ5_MT5zOvnVTmz28qPoEhc",
    authDomain: "uppproject-5285f.firebaseapp.com",
    projectId: "uppproject-5285f",
    storageBucket: "uppproject-5285f.appspot.com",
    messagingSenderId: "767086424852",
    appId: "1:767086424852:web:e894c9808a6270f0920801",
    measurementId: "G-9PNCTXHDF6"
  });


new Vue({
    router,
    store,
    vuetify,
    render: (h) => h(App)
}).$mount("#app");