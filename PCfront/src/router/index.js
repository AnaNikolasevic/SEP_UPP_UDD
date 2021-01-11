import Vue from 'vue'
import VueRouter from 'vue-router'
import HomePage from '../views/HomePage.vue'
import Payment from '../views/Payment.vue'
import AddPayment from '../views/AddPayment.vue'
import Success from '../components/status/success.vue'
import Error from '../components/status/error.vue'
import AikBankForm from '../views/AikBankForm.vue' 

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
        path: '/aikBank',
        name: 'AikBankForm',
        component: AikBankForm
    },
    {
        path: '/addPayment',
        name: 'addPayment',
        component: AddPayment
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