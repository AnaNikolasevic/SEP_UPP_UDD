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
        }
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
    },
    actions: {},
    modules: {}
})