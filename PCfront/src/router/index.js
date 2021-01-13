import Vue from 'vue'
import VueRouter from 'vue-router'
import HomePage from '../views/HomePage.vue'
import PaypalSuccess from '../components/status/PaypalSuccess.vue'
import PaypalError from '../components/status/PaypalError.vue'
import AddPayment from '../views/AddPayment.vue'
import BitcoinSuccess from '../components/status/BitcoinSuccess.vue'
import BitcoinError from '../components/status/BitcoinError.vue'
import AikBankForm from '../views/AikBankForm.vue'

Vue.use(VueRouter)

const routes = [{
        path: '/',
        name: 'HomePage',
        component: HomePage
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
        path: '/bitcoinSuccess',
        name: 'bitcoinSuccess',
        component: BitcoinSuccess
    },
    {
        path: '/bitcoinError',
        name: 'bitcoinError',
        component: BitcoinError
    },
    {
        path: '/paypalSuccess',
        name: 'paypalSuccess',
        component: PaypalSuccess
    },
    {
        path: '/paypalError',
        name: 'paypalError',
        component: PaypalError
    }

]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router