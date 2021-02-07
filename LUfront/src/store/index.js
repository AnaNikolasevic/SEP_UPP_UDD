import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex)

export default new Vuex.Store({
    plugins: [createPersistedState()],
    state: {
        processID: "",
        user: {
            username: "",
            role: "none"
        },
        booksInCart: [],
        purchasedBooks: []
    },

    mutations: {
        addProcessID(state, procesID) {
            state.processID = procesID;
        },
        login(state, user) {
            state.user.username = user.username;
            state.user.role = user.role;

        },
        logout(state) {
            state.user = {}
        },
        addBooksInCart(state, book) {
        // mutate state
            state.booksInCart.push(book);
            
        },
        deleteBook(state, book) {
            var index = state.booksInCart.findIndex((c) => c.id == book.id);
            state.booksInCart.splice(index, 1);
        },
        deleteAll(state) {
            state.booksInCart = [];
        },
        addPurchasedBooks(state, book) {
        // mutate state
            state.purchasedBooks.push(book);         
        },
        deleteAllPurchasedBooks(state) {
            state.purchasedBooks = [];
        }
    },
    actions: {},
    modules: {}
})