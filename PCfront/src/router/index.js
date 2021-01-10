import Vue from 'vue'
import VueRouter from 'vue-router'
import HomePage from '../views/HomePage.vue'
import Payment from '../views/Payment.vue'
import Success from '../components/status/success.vue'
import Error from '../components/status/error.vue'

Vue.use(VueRouter)

const routes = [{
        path: '/',
        name: 'HomePage',
        component: HomePage
    },
    {
        path: '/payment',
        name: 'payment',
        component: Payment
    },
    {
        path: '/success',
        name: 'success',
        component: Success
    },
    {
        path: '/error',
        name: 'error',
        component: Error
    }

]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router