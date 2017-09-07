import Vue from 'vue'
import VueRouter from 'vue-router'
import routes from './router/router'
import store from './store/'
import 'font-awesome/css/font-awesome.min.css'
import './style/common'
import './style/element.ui.extend.css'

require('./config/gt.js')

Vue.use(VueRouter)
const router = new VueRouter({
    routes
})

new Vue({
    router,
    store
}).$mount('#app')