import {createApp} from 'vue'
import App from './App.vue'
import router from "@/router";
import store from "@/store";
import VueGoodTablePlugin from 'vue-good-table-next';
import axios from "axios";
import VueAxios from 'vue-axios'

//Vue-good-table
import 'vue-good-table-next/dist/vue-good-table-next.css'

// Vuetify
import 'vuetify/styles'
import '@mdi/font/css/materialdesignicons.css'
import {createVuetify} from 'vuetify'
import * as components from 'vuetify/components'
import * as directives from 'vuetify/directives'
import { aliases, mdi } from 'vuetify/iconsets/mdi'

// Mask for phone
import {TheMask} from 'vue-the-mask'

//Style
import "./assets/style.css";

const vuetify = createVuetify({
    components,
    directives,
    icons: {
        defaultSet: 'mdi',
        aliases,
        sets: {
            mdi,
        }
    },
})

createApp(App)
    .use(router)
    .use(store)
    .use(vuetify)
    .use(VueGoodTablePlugin)
    .use(TheMask)
    .use(VueAxios, axios)
    .mount('#app')