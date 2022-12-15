import { createStore } from 'vuex'

const store = createStore({
    state: {
        counter: 0
    },

    getters: {
        counter(state) {
            return state.counter
        }
    }



})

export default store;