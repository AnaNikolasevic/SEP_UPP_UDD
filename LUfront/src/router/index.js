import Vue from 'vue'
import VueRouter from 'vue-router'
import HomePage from '../views/HomePage.vue'
import ActivationLink from '../views/ActivationLink.vue'
import Cart from '../components/Cart.vue'
import MandatoryUpload from '../components/MandatoryUpload.vue'
import MandatoryBooks from '../components/MandatoryBooks.vue'
import BookPreview from "../views/editor/BookPreview.vue";
import BookPlagiarism from "../views/editor/BookPlagiarism.vue"
import ChooseToSendToBetaReaders from "../components/editor/ChooseToSendToBetaReaders.vue"
import ChooseBetaReaders from "../components/editor/ChooseBetaReaders.vue"


Vue.use(VueRouter)

const routes = [{
        path: '/',
        name: 'HomePage',
        component: HomePage
    },
    {
        path: "/activationForm/:token",
        name: "ActivationLink",
        component: ActivationLink,
    },
    {
        path: '/cart',
        name: 'Cart',
        component: Cart
    },
    {
        path: "/activationForm/:token",
        name: "ActivationLink",
        component: ActivationLink,
    },
    {
        path: '/cart',
        name: 'Cart',
        component: Cart
    },
    {
        path: '/mandatoryUpload',
        name: 'MandatoryUpload',
        component: MandatoryUpload
    },
    {
        path: '/mandatoryBooks',
        name: 'MandatoryBooks',
        component: MandatoryBooks
    },
    {
        path: "/bookPreview",
        name: "BookPreview",
        component: BookPreview,
    },
    {
        path: "/bookPlagiarism",
        name: "BookPlagiarism",
        component: BookPlagiarism,
    },
    {
        path: "/chooseToSendToBetaReaders",
        name: "ChooseToSendToBetaReaders",
        component: ChooseToSendToBetaReaders,
    },
    {
        path: "/chooseBetaReaders",
        name: "ChooseBetaReaders",
        component: ChooseBetaReaders,
    },

]

const router = new VueRouter({
    mode: 'history',
    //base: process.env.BASE_URL,
    routes
})

export default router