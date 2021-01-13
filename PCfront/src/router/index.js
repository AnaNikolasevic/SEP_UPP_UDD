import Vue from 'vue'
import VueRouter from 'vue-router'
import HomePage from '../views/HomePage.vue'
import Payment from '../views/Payment.vue'
import Success from '../components/status/success.vue'
import Error from '../components/status/error.vue'
import AikBankForm from '../views/AikBank/AikBankForm.vue' 
import AikBankSuccess from '../views/AikBank/SuccesUrl.vue' 
import AikBankFailed from '../views/AikBank/FailedUrl.vue' 
import AikBankError from '../views/AikBank/ErrorUrl.vue' 


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
        path: '/aikBank/:id',
        name: 'AikBankForm',
        component: AikBankForm
    },
    {
        path: '/aikBank/success/:id',
        name: 'AikBankSuccess',
        component: AikBankSuccess
    },
    {
        path: '/aikBank/failed/:id',
        name: 'AikBankFailed',
        component: AikBankFailed
    },
    {
        path: '/aikBank/error/:id',
        name: 'AikBankError',
        component: AikBankError
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