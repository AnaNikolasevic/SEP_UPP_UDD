import Vue from 'vue'
import VueRouter from 'vue-router'
import HomePage from '../views/HomePage.vue'
import AikBankForm from '../views/AikBank/AikBankForm.vue' 
import AikBankSuccess from '../views/AikBank/SuccesUrl.vue' 
import AikBankFailed from '../views/AikBank/FailedUrl.vue' 
import AikBankError from '../views/AikBank/ErrorUrl.vue' 
import PaypalSuccess from '../components/status/PaypalSuccess.vue'
import PaypalError from '../components/status/PaypalError.vue'
import AddPayment from '../views/AddPayment.vue'
import BitcoinSuccess from '../components/status/BitcoinSuccess.vue'
import BitcoinError from '../components/status/BitcoinError.vue'

Vue.use(VueRouter)

const routes = [{
        path: '/',
        name: 'HomePage',
        component: HomePage
    },
    {
        path: '/aikBank/:id',
        name: 'AikBankForm',
        component: AikBankForm
    },
    {
        path: '/aikBankSuccess',
        name: 'AikBankSuccess',
        component: AikBankSuccess
    },
    {
        path: '/aikBankFailed',
        name: 'AikBankFailed',
        component: AikBankFailed
    },
    {
        path: '/aikBankError',
        name: 'AikBankError',
        component: AikBankError
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